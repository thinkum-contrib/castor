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
 * Copyright 1999 (C) Intalio, Inc. All Rights Reserved.
 *
 * $Id$
 */
package org.exolab.castor.tests.framework;

import java.util.Random;
import java.util.Vector;
import java.util.ArrayList;
import org.exolab.castor.types.*;

import java.math.BigDecimal;

/**
 * An helper class to assist in the generation of random instance of a given
 * object model.
 *
 * @author <a href="mailto:gignoux@intalio.com">Sebastien Gignoux</a>
 * @version $Revision$ $Date: 2006-04-13 06:47:36 -0600 (Thu, 13 Apr 2006) $
 */
public class RandomHelper {

    /**
     * The seed which was used to initialize the pseudo-random number generator
     */
    private static long _seed;

    /**
     * The pseudo random number generator.
     */
    private static Random _rand;

    static {
        _seed = System.currentTimeMillis();
        _rand = new Random(_seed);
    }

    /**
     * The maximum length of a string generated by rndString()
     */
    private final static int MAX_STRING_LENGTH = 50;

    /**
     * The maximum length of a collection (like a Vector) generated by rndString()
     */
    private final static int MAX_COLLECTION_LENGTH = 50;

    /**
     * List of the charactere that can be used to compose a string
     */
    private final static String PRINTABLE_CHAR = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890-_:.,=+~!@#$%^&*()[]{}\\|?";


    /**
     * Create an array of int of random length and populate it.
     *
     * @param array the array to populate
     * @param c not used
     */
    public static int[] getRandom(int[] array, Class c)
        throws InstantiationException, IllegalAccessException {

        int size = _rand.nextInt(MAX_COLLECTION_LENGTH);

        int[] ret = new int[size];

        for (int i=0; i<size; ++i)
            ret[i] = _rand.nextInt();

        return ret;
    }

    /**
     * Create an array of String of random length and populate it.
     *
     * @param array the array to populate
     * @param c not used
     */
    public static String[] getRandom(String[] array, Class c)
        throws InstantiationException, IllegalAccessException {

        int size = _rand.nextInt(MAX_COLLECTION_LENGTH);

        String[] ret = new String[size];

        for (int i=0; i<size; ++i)
            ret[i] = getRandom(new String(), null);

        return ret;
    }

    /**
     * Create an array of type c of random length and populate it. If the class
     * to put into the vector implement CastorTestable, randomizeFields() will
     * be called on the objects.
     *
     * @param array the array to populate
     * @param c the type of object to put in the array
     */
    public static Object[] getRandom(Object[] array, Class c)
        throws InstantiationException, IllegalAccessException {

        int size = _rand.nextInt(MAX_COLLECTION_LENGTH);

        Object[] ret = new Object[size];

        for (int i=0; i<size; ++i) {
            ret[i] = c.newInstance();
            if (CastorTestable.class.isAssignableFrom(c))
                ((CastorTestable)ret[i]).randomizeFields();
        }

        return ret;
    }


    /**
     * Create a vector of random length and populate it. If the class to put
     * into the vector implement CastorTestable, randomizeFields() will be
     * called on the objects.
     *
     * @param vect the vector to populate
     * @param c the type of object to put in the vector
     */
    public static Vector getRandom(Vector vect, Class c)
        throws InstantiationException, IllegalAccessException {

        int size = _rand.nextInt(MAX_COLLECTION_LENGTH);

        if (vect == null)
            vect = new Vector();

        for (int i=0; i<size; ++i) {
            Object obj = c.newInstance();
            vect.add(obj);
            if (CastorTestable.class.isAssignableFrom(c))
                ((CastorTestable)obj).randomizeFields();
        }

        return vect;
    }

    /**
     * Create an ArrayList of random length and populate it. If the class of the
     * object contained into the vector implement CastorTestable,
     * randomizeFields() will be called on the objects.
     *
     * @param al the ArrayList to populate
     * @param c the type of object to put in the vector
     */
    public static ArrayList getRandom(ArrayList al, Class c)
        throws InstantiationException, IllegalAccessException {
        return new ArrayList(RandomHelper.getRandom(new Vector(al), c));
    }


    /**
     * Returns a random string.
     * @returns a random string
     */
    public static String getRandom(String s, Class c) {

        int size = 1 + _rand.nextInt(MAX_STRING_LENGTH - 1);

        char[] data = new char[size];

        for (int i=0; i<size; ++i)
            data[i] = rndPrintableChar();

        return new String(data);
    }

    /**
     * Returns a random java.util.date.
     * @returns a random java.util.Date
     */
    public static java.util.Date getRandom(java.util.Date date, Class c) {

        long milli  = _rand.nextLong();
        return new java.util.Date(milli);
    }

    /**
     * Returns a random Castor timeDuration
     * @returns a random Castor timeDuration
     */
    public static TimeDuration getRandom(TimeDuration date, Class c) {
        long randLong = _rand.nextInt();
        randLong = (randLong > 0)?randLong : -randLong;
        return new TimeDuration(randLong);
    }

    /**
     * Returns a random Castor recurringDuration
     * @returns a random Castor recurringDuration
     */
    public static RecurringDuration getRandom(RecurringDuration recurring, Class c) {
        short randShort;
        long randLong = _rand.nextLong();
        TimeDuration randDuration = new TimeDuration(randLong);
        RecurringDuration result = new RecurringDuration(randDuration, randDuration);
        short[] values = new short[10];
        //only positive values are allowed
        //century
        randShort = (short) _rand.nextInt(99);
        values[0] = (randShort > 0)? randShort:(short)-randShort;
        //year
        randShort = (short)_rand.nextInt(99);
        values[1] = (randShort > 0)? randShort:(short)-randShort;
        //month
        randShort = (short)_rand.nextInt(12);
        values[2] = (randShort > 0)? randShort:(short)-randShort;
        //day
        randShort = (short)_rand.nextInt(30);
        values[3] = (randShort > 0)? randShort:(short)-randShort;
        //hour
        randShort = (short)_rand.nextInt(24);
        values[4] = (randShort > 0)? randShort:(short)-randShort;
        //minute
        randShort = (short)_rand.nextInt(60);
        values[5] = (randShort > 0)? randShort:(short)-randShort;
        //second
        randShort = (short)_rand.nextInt(60);
        values[6] = (randShort > 0)? randShort:(short)-randShort;
        //millisecond
        randShort = (short)_rand.nextInt(99);
        values[7] = (randShort > 0)? randShort:(short)-randShort;
        //time zone hour
        randShort = (short)_rand.nextInt(12);
        values[8] = randShort;
        //time zone minute
        randShort = (short)_rand.nextInt(60);
        values[9] = (randShort > 0)? randShort:(short)-randShort;
        result.setValues(values);
        values = null;
        randDuration = null;
        return result;
    }


    public static Object getRandom(Object obj, Class c) {
        try {
            obj = c.newInstance();
            if (obj.getClass().isAssignableFrom(CastorTestable.class))
              ((CastorTestable)obj).randomizeFields();
        } catch (Exception e) {
          //TODO: find a better handling
          e.printStackTrace();
        }
        return obj;
    }

    /**
     * Returns a random Castor recurringDuration
     * @returns a random Castor recurringDuration
     */
    public static BigDecimal getRandom(BigDecimal bg, Class c) {
        return new BigDecimal(_rand.nextDouble());
    }

    /**
     * Returns a random int.
     * @returns a random int
     */
    public static int getRandom(int i) {
        return _rand.nextInt();
    }

    /**
     * Returns a random float.
     * @returns a random float
     */
    public static float getRandom(float f) {
        return _rand.nextFloat();
    }


    /**
     * Returns a random boolean.
     * @returns a random boolean
     */
    public static boolean getRandom(boolean b) {
        return _rand.nextBoolean();
    }

    /**
     * Returns a random long.
     * @returns a random long
     */
    public static long getRandom(long l) {
        return _rand.nextLong();
    }

    /**
     * Returns a random double.
     * @returns a random double
     */
    public static double getRandom(double d) {
        return _rand.nextDouble();
    }

    /**
     * Returns a random printable char.
     * @returns a random printable char
     */
    public static char getRandom(char c) {
        return rndPrintableChar();
    }

    /**
     * Returns a random byte
     * @returns a random byte
     */
    public static byte getRandom(byte b) {
        byte[] tmp = new byte[1]; // TODO: Cache more...
        _rand.nextBytes(tmp);
        return tmp[0];
    }


    /**
     * Return randomly true or false with the same propability. 
     */
    public static boolean flip() {
        return _rand.nextBoolean();
    }

    /**
     * Return randomly true with the probility p. 
     */
    public static boolean flip(double p) {
        return (_rand.nextDouble() < p)? true : false;
    }

    /**
     * Returns a random printable character from the PRINTABLE_CHAR string
     * @returns a random printable character from the PRINTABLE_CHAR string
     */
    public static char rndPrintableChar() {
        return PRINTABLE_CHAR.charAt(_rand.nextInt(PRINTABLE_CHAR.length()));
    }

    /**
     * Returns the seed which was used to initialize the pseudo-random number
     * generator
     * @returns the seed which was used to initialize the pseudo-random number
     * generator
     */
    public static long getSeed() {
        return _seed;
    }

    /**
     * Reinitialize the random number generator with the given seed
     */
    public static void setSeed(long seed) {
        _seed = seed;
        _rand = new Random(_seed);
    }
}
