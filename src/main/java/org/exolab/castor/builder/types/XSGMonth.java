/**
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
 * Copyright 2001-2002(C) Intalio, Inc. All Rights Reserved.
 * $Id$
 */

package org.exolab.castor.builder.types;

import org.exolab.castor.xml.schema.SimpleType;
import org.exolab.castor.xml.schema.Facet;
import org.exolab.castor.types.GMonth;
import org.exolab.javasource.JSourceCode;
import org.exolab.javasource.JType;
import org.exolab.javasource.JClass;

import java.util.Enumeration;
import java.text.ParseException;

/**
 * The XML Schema gMonth type
 * @author <a href="mailto:blandin@intalio.com">Arnaud Blandin</a>
 * @version $Revision$ $Date: 2005-03-05 06:42:06 -0700 (Sat, 05 Mar 2005) $
 */

public class XSGMonth extends XSType {

    /**
     * The JType represented by this XSType
     */
    private static final JType jType
        = new JClass ("org.exolab.castor.types.GMonth");

    private GMonth _maxInclusive;
    private GMonth _maxExclusive;
    private GMonth _minInclusive;
    private GMonth _minExclusive;

    public XSGMonth() {
       super(XSType.GMONTH_TYPE);
    }

    /**
     * Returns the Java code necessary to create a new instance of the
     * JType associated with this XSType
     */
    public String newInstanceCode() {
         return "new "+getJType().getName()+"();";
    } //-- newInstanceCode

    public JType getJType() {
        return XSGMonth.jType;
    }

    /**
     * Returns the maximum exclusive value that this XSGMonth can hold.
     * @return the maximum exclusive value that this XSGMonth can hold. If
     * no maximum exclusive value has been set, Null will be returned
     * @see #getMaxInclusive
    **/
    public GMonth getMaxExclusive() {
        return _maxExclusive;
    } //-- getMaxExclusive

    /**
     * Returns the maximum inclusive value that this XSGMonth can hold.
     * @return the maximum inclusive value that this XSGMonth can hold. If
     * no maximum inclusive value has been set, Null will be returned
     * @see #getMaxExclusive
    **/
    public GMonth getMaxInclusive() {
        return _maxInclusive;
    } //-- getMaxInclusive


    /**
     * Returns the minimum exclusive value that this XSGMonth can hold.
     * @return the minimum exclusive value that this XSGMonth can hold. If
     * no minimum exclusive value has been set, Null will be returned
     * @see #getMinInclusive
     * @see #setMaxInclusive
    **/
    public GMonth getMinExclusive() {
        return _minExclusive;
    } //-- getMinExclusive

    /**
     * Returns the minimum inclusive value that this XSGMonth can hold.
     * @return the minimum inclusive value that this can XSGMonth hold. If
     * no minimum inclusive value has been set, Null will be returned
     * @see #getMinExclusive
    **/
    public GMonth getMinInclusive() {
        return _minInclusive;
    } //-- getMinInclusive

    /**
     * Sets the maximum exclusive value that this XSGMonth can hold.
     * @param max the maximum exclusive value this XSGMonth can be
     * @see #setMaxInclusive
    **/
    public void setMaxExclusive(GMonth max) {
        _maxExclusive = max;
        _maxInclusive = null;
    } //-- setMaxExclusive

    /**
     * Sets the maximum inclusive value that this XSGMonth can hold.
     * @param max the maximum inclusive value this XSGMonth can be
     * @see #setMaxExclusive
    **/
    public void setMaxInclusive(GMonth max) {
        _maxInclusive = max;
        _maxExclusive = null;
    } //-- setMaxInclusive


    /**
     * Sets the minimum exclusive value that this XSGMonth can hold.
     * @param min the minimum exclusive value this XSGMonth can be
     * @see #setMinInclusive
    **/
    public void setMinExclusive(GMonth min) {
        _minExclusive = min;
        _minInclusive = null;
    } //-- setMinExclusive

    /**
     * Sets the minimum inclusive value that this XSGMonth can hold.
     * @param min the minimum inclusive value this XSGMonth can be
     * @see #setMinExclusive
    **/
    public void setMinInclusive(GMonth min) {
        _minInclusive = min;
        _minExclusive = null;
    } //-- setMinInclusive

    public boolean hasMinimum() {
        return ( (_minInclusive != null) || (_minExclusive != null) );
    }


    public boolean hasMaximum() {
       return ( (_maxInclusive != null) || (_maxExclusive != null) );
    }

    /**
     * Reads and sets the facets for XSXSGMonth
     * override the readFacet method of XSType
     * @param simpleType the Simpletype containing the facets
     * @see org.exolab.castor.builder.types.XSType#getFacets
     */

    public void setFacets(SimpleType simpleType)
    {
        //-- copy valid facets
        Enumeration enumeration = getFacets(simpleType);
        while (enumeration.hasMoreElements()) {

            Facet facet = (Facet)enumeration.nextElement();
            String name = facet.getName();

            try {
                //-- maxExclusive
                if (Facet.MAX_EXCLUSIVE.equals(name))
                    this.setMaxExclusive(GMonth.parseGMonth(facet.getValue()));
                //-- maxInclusive
                else if (Facet.MAX_INCLUSIVE.equals(name))
                    this.setMaxInclusive(GMonth.parseGMonth(facet.getValue()));
                //-- minExclusive
                else if (Facet.MIN_EXCLUSIVE.equals(name))
                    this.setMinExclusive(GMonth.parseGMonth(facet.getValue()));
                //-- minInclusive
                else if (Facet.MIN_INCLUSIVE.equals(name))
                    this.setMinInclusive(GMonth.parseGMonth(facet.getValue()));
                //-- pattern
                else if (Facet.PATTERN.equals(name)) {
                    //do nothing for the moment
                    System.out.println("Warning: The facet 'pattern' is not currently supported for XSGMonth.");
                }
            } catch (ParseException e) {
                //not possible to set the facet properly
                //This can't happen since a ParseException would have been set
                //during the unmarshalling of the facets
                e.printStackTrace();
                return;
            }
        }//while

    }//setFacets
    
  	/**
	 * Creates the validation code for an instance of this XSType. The validation
     * code should if necessary create a newly configured TypeValidator, that
     * should then be added to a FieldValidator instance whose name is provided.
	 * 
	 * @param fixedValue a fixed value to use if any
	 * @param jsc the JSourceCode to fill in.
     * @param fieldValidatorInstanceName the name of the FieldValidator
     * that the configured TypeValidator should be added to.
	 */
	public void validationCode (JSourceCode jsc, String fixedValue, String fieldValidatorInstanceName) {
		if (jsc == null)
			jsc = new JSourceCode();
		jsc.add("DateTimeValidator typeValidator = new DateTimeValidator();");
		
		if (hasMinimum()) {
			jsc.add("try {");
			jsc.indent();
			GMonth min = getMinExclusive();
			if (min != null) {
				jsc.add(
					"org.exolab.castor.types. min ="
						+ "org.exolab.castor.types..parse("
						+ "\""
						+ min.toString()
						+ "\");");
				jsc.add("typeValidator.setMinExclusive(");
			} else {
				min = getMinInclusive();
				jsc.add(
					"org.exolab.castor.types. min ="
						+ "org.exolab.castor.types..parse("
						+ "\""
						+ min.toString()
						+ "\");");
				jsc.add("typeValidator.setMinInclusive(");
			}
			jsc.append("min");
			jsc.append(");");
			jsc.unindent();
			jsc.add("} catch (java.text.ParseException e) {");
			jsc.indent();
			jsc.add("System.out.println(e);");
			jsc.add("e.printStackTrace();");
			jsc.add("return;");
			jsc.unindent();
			jsc.add("}");

		} //hasMinimum?

		if (hasMaximum()) {
			jsc.add("try {");
			jsc.indent();
			GMonth max = getMaxExclusive();
			if (max != null) {
				jsc.add(
					"org.exolab.castor.types. max ="
						+ "org.exolab.castor.types..parse("
						+ "\""
						+ max.toString()
						+ "\");");
				jsc.add("typeValidator.setMaxExclusive(");
			} else {
				max = getMaxInclusive();
				jsc.add(
					"org.exolab.castor.types. max ="
						+ "org.exolab.castor.types.Date.parse("
						+ "\""
						+ max.toString()
						+ "\");");
				jsc.add("typeValidator.setMaxInclusive(");
			}
			jsc.append("max");
			jsc.append(");");
			jsc.unindent();
			jsc.add("} catch (java.text.ParseException e) {");
			jsc.indent();
			jsc.add("System.out.println(e);");
			jsc.add("e.printStackTrace();");
			jsc.add("return;");
			jsc.unindent();
			jsc.add("}");

		} //hasMaximum
		//-- pattern facet

		jsc.add(fieldValidatorInstanceName+".setValidator(typeValidator);");

	}
}