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

/**
 * Custom Class which is extended by SimpleNode. This class can be used to add
 * properties and methods to SimpleNode class generated by CastorQLParser.jjt
 * 
 * @author <a href="mailto:mailtoud AT gmail DOT com">Udai Gupta</a>
 * @author <a href="mailto:ralf DOT joachim AT syscon DOT eu">Ralf Joachim</a>
 * @version $Revision: 7121 $ $Date: 2006-04-25 16:09:10 -0600 (Tue, 25 Apr 2006) $
 * @since 1.3
 */
public class ExtendNode {
    // --------------------------------------------------------------------------
    
    /** The text. */
    private String _text;

    /** The kind. */
    private int _kind; 
    
    // --------------------------------------------------------------------------
    
    /**
     * Gets the text.
     * 
     * @return the text
     */
    public final String getText() {
        return _text;
    }

    /**
     * Sets the text.
     * 
     * @param text the new text
     */
    public final void setText(final String text) {
        _text = text;
    }

    /**
     * Gets the kind.
     * 
     * @return the kind
     */
    public final int getKind() {
        return _kind;
    }

    /**
     * Sets the kind.
     * 
     * @param kind the new kind
     */
    public final void setKind(final int kind) {
        _kind = kind;
    }
    
    // --------------------------------------------------------------------------
}
