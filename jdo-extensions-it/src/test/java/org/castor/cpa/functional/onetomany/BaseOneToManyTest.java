/*
 * Copyright 2008 Tobias Hochwallner
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
package org.castor.cpa.functional.onetomany;

import java.io.FileInputStream;

import javax.naming.spi.DirStateFactory.Result;

import junit.framework.TestCase;

import org.castor.cpa.functional.single.Book;
import org.dbunit.DefaultDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.xml.XmlDataSet;
import org.exolab.castor.jdo.Database;
import org.exolab.castor.jdo.JDOManager;
import org.exolab.castor.jdo.OQLQuery;
import org.exolab.castor.jdo.PersistenceException;
import org.exolab.castor.jdo.QueryResults;

/**
 * 
 * 
 * @author Tobias Hochwallner
 */
public abstract class BaseOneToManyTest extends TestCase {

    /**
     * JDOManager instance, for connecting to database.
     */
    protected JDOManager _jdo = null;
    private DefaultDatabaseTester dbtester = null;
    /**
     * Name of the initial dataset file.
     */
    private static final String DATA_SET_FILE = "OneToMany.xml";

    protected void setUp() throws Exception {
        super.setUp();
        setUpJDO();
        dbtester = new DefaultDatabaseTester(new DatabaseConnection(_jdo
                .getConnectionFactory().createConnection()));
        dbtester.setDataSet(new XmlDataSet(new FileInputStream(getClass()
                .getResource(DATA_SET_FILE).getFile())));
        dbtester.onSetup();
    }

    public abstract void setUpJDO();

    /**
     * Tests if loading an flat element from database works.
     * 
     * @throws Exception
     *             if something goes wrong
     */
    public void testLoadFlat() throws Exception {
        Database db = _jdo.getDatabase();
        assertNotNull(db);
        db.begin();

        Flat flat = (Flat) db.load(Flat.class, new Long(1));

        assertNotNull(flat);
        assertEquals(1, flat.getId());

        db.commit();
        db.close();
    }

    /**
     * Tests if loading an house element from database including a flat works.
     * 
     * @throws Exception
     *             if something goes wrong
     */
    public void testLoadHouseOneFlat() throws Exception {
        Database db = _jdo.getDatabase();
        assertNotNull(db);
        db.begin();

        House house = (House) db.load(House.class, new Long(100));
        assertNotNull(house);

        assertEquals(100, house.getId());

        Flat[] flats = house.getFlats();
        assertNotNull(flats);
        assertEquals(1, flats.length);

        Flat flat = house.getFlats()[0];
        assertEquals(1, flat.getId());

        db.commit();
        db.close();
    }

    /**
     * Tests if loading an house element from database including more flats
     * works.
     * 
     * @throws Exception
     *             if something goes wrong
     */
    public void testLoadHouseFlats() throws Exception {
        Database db = _jdo.getDatabase();
        assertNotNull(db);
        db.begin();

        House house = (House) db.load(House.class, new Long(101));
        assertNotNull(house);

        assertEquals(101, house.getId());

        Flat[] flats = house.getFlats();
        assertNotNull(flats);
        assertEquals(2, flats.length);

        db.commit();
        db.close();
    }

    /**
     * Tests a simple OQL query.
     * 
     * @throws Exception
     *             if db setup fails.
     */
    public void testSelectQuery() throws Exception {
        Database db;
        db = _jdo.getDatabase();
        assertNotNull(db);
        db.begin();
        OQLQuery query = db.getOQLQuery("SELECT h FROM "
                + House.class.getName() + " h WHERE h.flats.id = $1");
        query.bind(2);
        QueryResults result = query.execute();
        
        House e = (House) result.next();
        assertEquals(101, e.getId());
        assertEquals(2, e.getFlats().length);
        
        result.close();
        db.commit();
        db.close();
    }
}
