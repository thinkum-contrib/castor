/*
 * Copyright 2005 Tim Telcik, Werner Guttmann, Ralf Joachim
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
package org.castor.cache.distributed;

import org.castor.cache.AbstractCacheFactory;

/**
 * Implements {@link org.castor.cache.CacheFactory} for the {@link EHCache}
 * Implements {@link org.castor.cache.CacheFactory} for the {@link EHCache}
 * implementation of {@link org.castor.cache.Cache}.
 *
 * @param <K> the type of keys maintained by cache
 * @param <V> the type of cached values
 *
 * @author <a href="mailto:werner DOT guttmann AT gmx DOT net">Werner Guttmann</a>
 * @version $Revision$ $Date$
 * @since 1.0.1
 */
public final class EHCacheFactory<K, V> extends AbstractCacheFactory<K, V> {
    /**
     * {@inheritDoc}
     */
    public String getCacheType() { 
        return EHCache.TYPE;
    }
    
    /**
     * {@inheritDoc}
     */
    public String getCacheClassName() { 
        return EHCache.class.getName(); 
    }
}