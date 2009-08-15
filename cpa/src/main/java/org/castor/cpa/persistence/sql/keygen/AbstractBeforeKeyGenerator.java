/*
 * Copyright 2009 Ahmad Hassan, Ralf Joachim
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.castor.cpa.persistence.sql.keygen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.castor.core.util.Messages;
import org.castor.cpa.persistence.sql.engine.SQLStatementInsertCheck;
import org.castor.cpa.persistence.sql.query.Insert;
import org.castor.cpa.persistence.sql.query.QueryContext;
import org.castor.persist.ProposedEntity;
import org.exolab.castor.jdo.Database;
import org.exolab.castor.jdo.DuplicateIdentityException;
import org.exolab.castor.jdo.PersistenceException;
import org.exolab.castor.jdo.engine.SQLColumnInfo;
import org.exolab.castor.jdo.engine.SQLEngine;
import org.exolab.castor.jdo.engine.SQLFieldInfo;
import org.exolab.castor.jdo.engine.nature.ClassDescriptorJDONature;
import org.exolab.castor.mapping.ClassDescriptor;
import org.exolab.castor.persist.spi.Identity;
import org.exolab.castor.persist.spi.PersistenceFactory;

/**
 * Abstract Class that implements the KeyGenerator Interface for BEFORE_INSERT style. The key
 * generator is used for producing identities for objects before they are created in the
 * database.
 * 
 * @author <a href="mailto:ahmad DOT hassan AT gmail DOT com">Ahmad Hassan</a>
 * @author <a href="mailto:ralf DOT joachim AT syscon DOT eu">Ralf Joachim</a>
 * @version $Revision$ $Date: 2009-07-13 17:22:43 (Tue, 28 Jul 2009) $
 */
public abstract class AbstractBeforeKeyGenerator extends AbstractKeyGenerator {
    //-----------------------------------------------------------------------------------    

    /** The <a href="http://jakarta.apache.org/commons/logging/">Jakarta
     *  Commons Logging</a> instance used for all logging. */
    private static final Log LOG = LogFactory.getLog(AbstractBeforeKeyGenerator.class);
    
    /** Persistence factory for the database engine the entity is persisted in.
     *  Used to format the SQL statement. */
    private PersistenceFactory _factory;
    
    /** SQL engine for all persistence operations at entities of the type this
     * class is responsible for. Holds all required information of the entity type. */
    private SQLEngine _engine;
    
    /** Represents the engine type obtained from clas descriptor. */
    private String _engineType = null;
    
    /** Name of the Table extracted from Class descriptor. */
    private String _mapTo;
    
    /** QueryContext for SQL query building, specifying database specific quotations 
     *  and parameters binding. */
    private final QueryContext _ctx;

    //-----------------------------------------------------------------------------------    

    /**
     * Constructor.
     * 
     * @param factory  Persistence factory for the database engine the entity is persisted in.
     *  Used to format the SQL statement
     */
    public AbstractBeforeKeyGenerator(final PersistenceFactory factory) {
        _factory = factory;
        _ctx = new QueryContext(_factory);
    }
    
    //-----------------------------------------------------------------------------------    

    /**
     * {@inheritDoc}
     */
    public KeyGenerator buildStatement(final SQLEngine engine) {
        _engine = engine;
        ClassDescriptor clsDesc = _engine.getDescriptor();
        _engineType = clsDesc.getJavaClass().getName();
        _mapTo = new ClassDescriptorJDONature(clsDesc).getTableName();    
        Insert insert = new Insert(_mapTo);
      
        SQLColumnInfo[] ids = _engine.getColumnInfoForIdentities();        
        for (int i = 0; i < ids.length; i++) {
            insert.addInsert(ids[i].getName());
        }
        
        SQLFieldInfo[] fields = _engine.getInfo();
        for (int i = 0; i < fields.length; ++i) {
            if (fields[i].isStore()) {
                SQLColumnInfo[] columns = fields[i].getColumnInfo();
                for (int j = 0; j < columns.length; j++) {
                    insert.addInsert(columns[j].getName());          
                }
            }
        }        
        insert.toString(_ctx);
        
        if (LOG.isTraceEnabled()) {
            LOG.trace(Messages.format("jdo.creating", _engineType, _ctx.toString()));
        }
        
        return this;
    }
    
    /**
     * {@inheritDoc}
     */
    public Object executeStatement(final Database database, final Connection conn, 
            final Identity identity, final ProposedEntity entity) throws PersistenceException {
        SQLStatementInsertCheck lookupStatement = 
            new SQLStatementInsertCheck(_engine, _factory);    
        Identity internalIdentity = identity;
        SQLEngine extended = _engine.getExtends();
        PreparedStatement stmt = null;
        
        try {
            // must create record in the parent table first. all other dependents
            // are created afterwards. quick and very dirty hack to try to make
            // multiple class on the same table work.
            if (extended != null) {
                ClassDescriptor extDesc = extended.getDescriptor();
                if (!new ClassDescriptorJDONature(extDesc).getTableName().equals(_mapTo)) {
                    internalIdentity = extended.create(database, conn, entity, internalIdentity);
                }
            }
            
            // generate key before INSERT.
            internalIdentity = generateKey(database, conn, null);

            // we only need to care on JDBC 3.0 at after INSERT.
            stmt = conn.prepareStatement(_ctx.toString());
            
            //bind Identities
            bindIdentity(internalIdentity, stmt);

            //bind  Fields
            bindFields(entity, stmt);
            
            if (LOG.isDebugEnabled()) {
                LOG.debug(Messages.format("jdo.creating", _engineType, stmt.toString()));
            }

            stmt.executeUpdate();

            stmt.close();

            return internalIdentity;
        } catch (SQLException except) {
            LOG.fatal(Messages.format("jdo.storeFatal",  _engineType, _ctx.toString()), except);

            // check for duplicate key the old fashioned way, after the INSERT
            // failed to prevent race conditions and optimize INSERT times.
            lookupStatement.insertDuplicateKeyCheck(conn, internalIdentity);

            try {
                if (stmt != null) { stmt.close(); }
            } catch (SQLException except2) {
                LOG.warn("Problem closing JDBC statement", except2);
            }
            
            throw new PersistenceException(Messages.format("persist.nested", except), except);
        }
    }

    /**
     * Binds the identity values.
     * 
     * @param internalIdentity Identity values.
     * @param stmt PreapraedStatement containing the sql insert statement. 
     * @throws SQLException If a database access error occurs.
     * @throws PersistenceException If identity size mismatches.
     */
    public void bindIdentity(final Identity internalIdentity, final PreparedStatement stmt) 
    throws SQLException, PersistenceException {
        SQLColumnInfo[] ids = _engine.getColumnInfoForIdentities();
        if (internalIdentity.size() != ids.length) {
            throw new PersistenceException("Size of identity field mismatched!");
        }

        for (int i = 0; i < ids.length; i++) {
            _ctx.bindParameter(stmt, ids[i].getName(), ids[i].toSQL(internalIdentity.get(i)), 
                    ids[i].getSqlType());
        }
    }
    
    /**
     * Binds parameters values to the PreparedStatement.
     * 
     * @param entity Entity instance from which field values to be fetached to
     *               bind with sql insert statement.
     * @param stmt PreparedStatement object containing sql staatement.
     * @throws SQLException If a database access error occurs.
     * @throws PersistenceException If identity size mismatches.
     */
    private void bindFields(final ProposedEntity entity, final PreparedStatement stmt
            ) throws SQLException, PersistenceException {
        SQLFieldInfo[] fields = _engine.getInfo();
        for (int i = 0; i < fields.length; ++i) {
            SQLColumnInfo[] columns = fields[i].getColumnInfo();
            if (fields[i].isStore()) {
                Object value = entity.getField(i);
                if (value == null) {
                    for (int j = 0; j < columns.length; j++) {
                        _ctx.bindParameter(stmt, columns[j].getName(), null, 
                                columns[j].getSqlType());
                    }
                } else if (value instanceof Identity) {
                    Identity identity = (Identity) value;
                    if (identity.size() != columns.length) {
                        throw new PersistenceException("Size of identity field mismatch!");
                    }
                    for (int j = 0; j < columns.length; j++) {
                        _ctx.bindParameter(stmt, columns[j].getName(), 
                                columns[j].toSQL(identity.get(j)), columns[j].getSqlType());
                    }
                } else {
                    if (columns.length != 1) {
                        throw new PersistenceException("Complex field expected!");
                    }
                    _ctx.bindParameter(stmt, columns[0].getName(), columns[0].toSQL(value), 
                            columns[0].getSqlType());
                }
            }
        }
    }
    
    /**
     * Generates the key.
     * 
     * @param database Particular Database instance.
     * @param conn An open JDBC Connection. 
     * @param stmt PreparedStatement containing the SQL statement.
     * @return Identity that is generated.
     * @throws PersistenceException If fails to Generate key.
     */
    private Identity generateKey(final Database database, final Connection conn,
            final PreparedStatement stmt)
    throws PersistenceException {
        SQLColumnInfo id = _engine.getColumnInfoForIdentities()[0];

        // TODO [SMH]: Change KeyGenerator.isInSameConnection to KeyGenerator.useSeparateConnection?
        // TODO [SMH]: Move "if (_keyGen.isInSameConnection() == false)"
        //                 out of SQLEngine and into key-generator?
        Connection connection = conn;
        if (!this.isInSameConnection()) {
        connection = getSeparateConnection(database);
        }

        Properties prop = null;
        if (stmt != null) {
        prop = new Properties();
        prop.put("insertStatement", stmt);
        }

        try {
            Object identity;
            synchronized (connection) {
            identity = this.generateKey(connection, _mapTo, id.getName(), prop);
            }

            // TODO [SMH]: Move "if (identity == null)" into keygenerator.
            if (identity == null) {
            throw new PersistenceException(
            Messages.format("persist.noIdentity", _engineType));
            }

            return new Identity(id.toJava(identity));
        } finally {
            if (!this.isInSameConnection()) {
                closeSeparateConnection(connection);
            }
        }
    }
    
    //-----------------------------------------------------------------------------------        
}
