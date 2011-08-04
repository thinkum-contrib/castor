/*
 * Copyright 2005 Ralf Joachim
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
package org.castor.cache.hashbelt;

import java.util.Map;

/**
 * A perfectly ordinary hashbelt. Objects all go into the first container on the
 * belt, and make their way down over time until they fall off the end.
 * 
 * @author <a href="mailto:ralf DOT joachim AT syscon DOT eu">Ralf Joachim</a>
 * @version $Revision$ $Date$
 * @since 1.0
 */
public final class FIFOHashbelt extends AbstractHashbelt {
    //--------------------------------------------------------------------------

    /** The type of the cache. */
    public static final String TYPE = "fifo";
    
    //--------------------------------------------------------------------------
    // getters/setters for cache configuration

    /**
     * {@inheritDoc}
     */
    public String getType() { return TYPE; }

    //--------------------------------------------------------------------------
    // query operations of map interface

    /**
     * {@inheritDoc}
     */
    public Object get(final Object key) {
        if (key == null) { throw new NullPointerException("key"); }
        
        lock().readLock().lock();
        try {
            return getObjectFromCache(key);
        } finally {
            lock().readLock().unlock();
        }
    }
    
    //--------------------------------------------------------------------------
    // modification operations of map interface
    
    /**
     * {@inheritDoc}
     */
    public Object put(final Object key, final Object value) {
        if (key == null) { throw new NullPointerException("key"); }
        if (value == null) { throw new NullPointerException("value"); }
            
        lock().writeLock().lock();
        try {
            return putObjectIntoCache(key, value);
        } finally {
            lock().writeLock().unlock();
        }
    }

    /**
     * {@inheritDoc}
     */
    public Object remove(final Object key) {
        if (key == null) { throw new NullPointerException("key"); }

        lock().writeLock().lock();
        try {
            return removeObjectFromCache(key);
        } finally {
            lock().writeLock().unlock();
        }
    }

    //--------------------------------------------------------------------------
    // bulk operations of map interface
    
    /**
     * {@inheritDoc}
     */
    public void putAll(final Map<? extends Object, ? extends Object> map) {
        if (map.containsKey(null)) { throw new NullPointerException("key"); }
        if (map.containsValue(null)) { throw new NullPointerException("value"); }

        lock().writeLock().lock();
        try {
            for (Entry<? extends Object, ? extends Object> entry : map.entrySet()) {
                putObjectIntoCache(entry.getKey(), entry.getValue());
            }
        } finally {
            lock().writeLock().unlock();
        }
    }

    //--------------------------------------------------------------------------
}
