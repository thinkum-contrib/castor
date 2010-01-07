/*
 * Copyright 2008 Udai Gupta, Ralf Joachim
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
package org.castor.cpa.query.ejbql;

import java.io.UnsupportedEncodingException;

import org.castor.cpa.query.ParseException;
import org.castor.cpa.query.TokenManagerError;

import junit.framework.TestCase;

/**
 * Junit test for test dump method of SimpleNode generated by parser.
 * 
 * @author <a href="mailto:mailtoud AT gmail DOT com">Udai Gupta</a>
 * @author <a href="mailto:ralf DOT joachim AT syscon DOT eu">Ralf Joachim</a>
 * @version $Revision: 7121 $ $Date: 2006-04-25 16:09:10 -0600 (Tue, 25 Apr 2006) $
 * @since 1.3
 */
public class TestDump extends TestCase {
      //--------------------------------------------------------------------------
    
    /**
       * Test tree dump.
       * 
       * @throws UnsupportedEncodingException the unsupported encoding exception
       */
      public static void testTreeDump() throws UnsupportedEncodingException {
      try {  
       
      //Mess the Query to see the exceptions    
      String oql = "SelecT DisTinct o.item from org.castor.cpa.query.Foo as o"
            + " where o.deleted <= TimeStamp ('2008-08-05 03:22:04.000')";
            //+ " order by o.name, o.id desc"
           // + " limit ?4 offset ?2";
      
      EjbQLParserAdapter parser = new EjbQLParserAdapter();
        
       SimpleNode root = parser.getSimpleNode(oql);
       root.dump("");
        
        
      } catch (ParseException e) { 
          e.printStackTrace(System.out);
      } catch (TokenManagerError tkme) {
        tkme.printStackTrace(System.out); 
      }
      
    }
    
    //--------------------------------------------------------------------------
}
