/*
 * Redistribution and use of this software and associated documentation
 * ("Software"), with or without modification, are permitted provided
 * that the following conditions are met:
 *
 * 1. Redistributions of source code must retain copyright
 *    statements and notices.  Redistributions must also contain a
 *    copy of this document.
 *
 * 2. Redistributions in binary form must reproduce the
 *    above copyright notice, this list of conditions and the
 *    following disclaimer in the documentation and/or other
 *    materials provided with the distribution.
 *
 * 3. The name "Exolab" must not be used to endorse or promote
 *    products derived from this Software without prior written
 *    permission of Intalio, Inc.  For written permission,
 *    please contact info@exolab.org.
 *
 * 4. Products derived from this Software may not be called "Exolab"
 *    nor may "Exolab" appear in their names without prior written
 *    permission of Intalio, Inc. Exolab is a registered
 *    trademark of Intalio, Inc.
 *
 * 5. Due credit should be given to the Exolab Project
 *    (http://www.exolab.org/).
 *
 * THIS SOFTWARE IS PROVIDED BY INTALIO, INC. AND CONTRIBUTORS
 * ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL
 * INTALIO, INC. OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Copyright 2000-2003 (C) Intalio, Inc. All Rights Reserved.
 *
 * $Id$
 */
package org.exolab.castor.types;

import org.exolab.castor.mapping.FieldDescriptor;
import org.exolab.castor.mapping.ValidityException;
import org.exolab.castor.xml.NodeType;
import org.exolab.castor.xml.XMLFieldDescriptor;
import org.exolab.castor.xml.XMLFieldHandler;
import org.exolab.castor.xml.util.XMLFieldDescriptorImpl;

/**
 * The Century Descriptor
 * @author <a href="kvisco@intalio.com">Keith Visco</a>
 * @version $Revision$ $Date: 2005-03-05 06:42:06 -0700 (Sat, 05 Mar 2005) $
 * @see TimeDurationDescriptor
 * @deprecated since Castor 1.0.6 since this type is not in any recommendation.
 */
public class CenturyDescriptor extends BaseDescriptor {

    /** The name of the XML element. */
    private static final String XML_NAME = "century";
    /** Our field descriptor. */
    private static final XMLFieldDescriptorImpl CONTENT_DESCRIPTOR;
    /** Our field descriptor array.  Lists the fields we describe. */
    private static final FieldDescriptor[] FIELDS;

    static {
        CONTENT_DESCRIPTOR = new XMLFieldDescriptorImpl(
                String.class, "content", "content", NodeType.Text);
        CONTENT_DESCRIPTOR.setHandler(new CenturyDescriptor().new CenturyFieldHandler());
        FIELDS = new FieldDescriptor[1];
        FIELDS[0] = CONTENT_DESCRIPTOR;
    }

    //----------------/
    //- Constructors -/
    //----------------/

    public CenturyDescriptor() {
        super(XML_NAME, Century.class);
    } //--CenturyDescriptor

    //------------------/
    //- Public Methods -/
    //------------------/

    /**
     * Returns the XMLFieldDescriptor for the member that should be marshalled
     * as text content.
     *
     * @return the XMLFieldDescriptor for the member that should be marshalled
     *         as text content.
     */
    public XMLFieldDescriptor getContentDescriptor() {
        return CONTENT_DESCRIPTOR;
    } // getContentDescriptor

    /**
     * Returns a list of fields represented by this descriptor.
     *
     * @return A list of fields
     */
    public FieldDescriptor[] getFields() {
        return FIELDS;
    } //-- getFields

    /**
     * A specialized FieldHandler for the XML Schema Century related types
     *
     * @author <a href="blandin@intalio.com">Arnaud Blandin</a>
     * @version $Revision$ $Date: 2005-03-05 06:42:06 -0700 (Sat, 05 Mar 2005) $
     */
    class CenturyFieldHandler extends XMLFieldHandler {

        //----------------/
        //- Constructors -/
        //----------------/

        /**
         * Creates a new TimeFieldHandler
         */
        public CenturyFieldHandler() {
            super();
        } //-- CenturyFieldHandler

        //------------------/
        //- Public Methods -/
        //------------------/

        /**
         * Returns the value of the field associated with this descriptor from
         * the given target object.
         *
         * @param target
         *            the object to get the value from
         * @return the value of the field associated with this descriptor from
         *         the given target object.
         */
        public Object getValue(Object target) throws java.lang.IllegalStateException {
            //-- check for Century class  -- add later
            Century  century = (Century) target;
            return century.toString();
        } //-- getValue

        /**
         * Sets the value of the field associated with this descriptor.
         * @param target the object in which to set the value
         * @param value the value of the field
         */
        public void setValue(Object target, Object value) throws java.lang.IllegalStateException {
            if (! (target instanceof Century) ) {
               //-- throw exception
            }

            Century centuryTarget = (Century) target;

            if (value == null) {
               /// do something
            }

            //-- update current instance of century with new century
            try {
                Century temp = Century.parseCentury(value.toString()) ;
                centuryTarget.setCentury(temp.getCentury());
            } catch (java.text.ParseException ex) {
                //ignore for now
            }
        } //-- setValue

        public void resetValue(Object target) throws java.lang.IllegalStateException {
            // Nothing to do?
        }

        /**
         * Checks the field's validity. Returns successfully if the field can be
         * stored, is valid, etc, throws an exception otherwise.
         *
         * @param object
         *            The object
         * @throws ValidityException
         *             The field is invalid, is required and null, or any other
         *             validity violation
         * @throws IllegalStateException
         *             The Java object has changed and is no longer supported by
         *             this handler, or the handler is not compatiable with the
         *             Java object
         */
        public void checkValidity(Object object) throws ValidityException, IllegalStateException {
            // nothing to do?
        } //-- checkValidity

        /**
         * Creates a new instance of the object described by this field.
         *
         * @param parent
         *            The object for which the field is created
         * @return A new instance of the field's value
         * @throws IllegalStateException
         *             This field is a simple type and cannot be instantiated
         */
        public Object newInstance(Object parent) throws IllegalStateException {
            return new Century();
        } //-- newInstance

    } //-- CenturyFieldHandler

} //-- CenturyDescriptor
