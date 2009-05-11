/*
 * Copyright 2006 Assaf Arkin, Thomas Yip, Bruce Snyder, Werner Guttmann, Ralf Joachim
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
 *
 * $Id$
 */
package org.exolab.castor.jdo.engine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.castor.core.util.Messages;
import org.exolab.castor.jdo.PersistenceException;
import org.exolab.castor.jdo.engine.nature.ClassDescriptorJDONature;
import org.exolab.castor.persist.spi.Identity;
import org.exolab.castor.persist.spi.PersistenceFactory;
import org.exolab.castor.persist.spi.QueryExpression;

public final class SQLStatementRemove {
    /** The <a href="http://jakarta.apache.org/commons/logging/">Jakarta
     *  Commons Logging</a> instance used for all logging. */
    private static final Log LOG = LogFactory.getLog(SQLStatementRemove.class);

    private final PersistenceFactory _factory;
    
    private final String _type;

    private final String _mapTo;

    private final SQLColumnInfo[] _ids;

    private String _statement;

    public SQLStatementRemove(final SQLEngine engine, final PersistenceFactory factory) {
        _factory = factory;
        _type = engine.getDescriptor().getJavaClass().getName();
        _mapTo = new ClassDescriptorJDONature(engine.getDescriptor()).getTableName();
        
        //Get ID's list from engine provided
        _ids = engine.getColumnInfoForIdentities();
        
        buildStatement();
    }
    
    private void buildStatement() {
        StringBuffer sql = new StringBuffer("DELETE FROM ");
        sql.append(_factory.quoteName(_mapTo));
        sql.append(JDBCSyntax.WHERE);
        for (int i = 0; i < _ids.length; i++) {
            if (i > 0) { sql.append(" AND "); }
            sql.append(_factory.quoteName(_ids[i].getName()));
            sql.append(QueryExpression.OP_EQUALS);
            sql.append(JDBCSyntax.PARAMETER);
        }
        _statement = sql.toString();
        
        if (LOG.isTraceEnabled()) {
            LOG.trace(Messages.format("jdo.removing", _type, _statement));
        }
    }

    public Object executeStatement(final Connection conn, final Identity identity)
    throws PersistenceException {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(_statement);
            
            if (LOG.isTraceEnabled()) {
                LOG.trace(Messages.format("jdo.removing", _type, stmt.toString()));
            }

            int count = 1;

            // bind the identity of the preparedStatement
            for (int i = 0; i < _ids.length; i++) {
                stmt.setObject(count++, _ids[i].toSQL(identity.get(i)));
            }

            if (LOG.isDebugEnabled()) {
                LOG.debug(Messages.format("jdo.removing", _type, stmt.toString()));
            }

            int result = stmt.executeUpdate();
            if (result < 1) {
                throw new PersistenceException("Object to be deleted does not exist! " + identity);
            }
        } catch (SQLException except) {
            LOG.fatal(Messages.format("jdo.deleteFatal", _type, _statement), except);
            throw new PersistenceException(Messages.format("persist.nested", except), except);
        } finally {
            try {
                if (stmt != null) { stmt.close(); }
            } catch (Exception e) {
                LOG.warn("Problem closing JDBC statement", e);
            }
        }
        
        return null;
    }
}
