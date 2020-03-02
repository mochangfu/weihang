/*
package com.hg.gama.boot.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.hg.gama.gamautil.numasutil.cache.LianfanCaffeineCacheManager;
import com*/
/*
package com.hg.gama.gamautil.numasutil.cache;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.CaffeineSpec;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LianfanCaffeineCacheManager implements CacheManager {

    private final ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<>(16);

    private boolean dynamic = true;

    private Caffeine<Object, Object> cacheBuilder = Caffeine.newBuilder();

    private com.github.benmanes.caffeine.cache.CacheLoader<Object, Object> cacheLoader;

    private boolean allowNullValues = true;


    *//*

*/
/**
 * Construct a dynamic CaffeineCacheManager,
 * lazily creating cache instances as they are being requested.
 *//*
*/
/*

    public LianfanCaffeineCacheManager() {
    }

    *//*

*/
/**
 * Construct a static CaffeineCacheManager,
 * managing caches for the specified cache names only.
 *//*
*/
/*

    public LianfanCaffeineCacheManager(String... cacheNames) {
        setCacheNames(Arrays.asList(cacheNames));
    }


    *//*

*/
/**
 * Specify the set of cache names for this CacheManager's 'static' mode.
 * <p>The number of caches and their names will be fixed after a call to this method,
 * with no creation of further cache regions at runtime.
 * <p>Calling this with a {@code null} collection argument resets the
 * mode to 'dynamic', allowing for further creation of caches again.
 *//*
*/
/*

    public void setCacheNames(Collection<String> cacheNames) {
        if (cacheNames != null) {
            for (String name : cacheNames) {
                cacheMap.put(name, createCaffeineCache(name));
            }
            dynamic = false;
        } else {
            dynamic = true;
        }
    }

    *//*

*/
/**
 * Set the Caffeine to use for building each individual
 * {@link LianfanCaffeineCache} instance.
 *
 * @see #createNativeCaffeineCache
 * @see com.github.benmanes.caffeine.cache.Caffeine#build()
 *//*
*/
/*

    public void setCaffeine(Caffeine<Object, Object> caffeine) {
        Assert.notNull(caffeine, "Caffeine must not be null");
        doSetCaffeine(caffeine);
    }

    *//*

*/
/**
 * Set the {@link CaffeineSpec} to use for building each individual
 * {@link LianfanCaffeineCache} instance.
 *
 * @see #createNativeCaffeineCache
 * @see com.github.benmanes.caffeine.cache.Caffeine#from(CaffeineSpec)
 *//*
*/
/*

    public void setCaffeineSpec(CaffeineSpec caffeineSpec) {
        doSetCaffeine(Caffeine.from(caffeineSpec));
    }

    *//*

*/
/**
 * Set the Caffeine cache specification String to use for building each
 * individual {@link LianfanCaffeineCache} instance. The given value needs to
 * comply with Caffeine's {@link CaffeineSpec} (see its javadoc).
 *
 * @see #createNativeCaffeineCache
 * @see com.github.benmanes.caffeine.cache.Caffeine#from(String)
 *//*
*/
/*

    public void setCacheSpecification(String cacheSpecification) {
        doSetCaffeine(Caffeine.from(cacheSpecification));
    }

    *//*

*/
/**
 * Set the Caffeine CacheLoader to use for building each individual
 * {@link LianfanCaffeineCache} instance, turning it into a LoadingCache.
 *
 * @see #createNativeCaffeineCache
 * @see com.github.benmanes.caffeine.cache.Caffeine#build(com.github.benmanes.caffeine.cache.CacheLoader)
 * @see com.github.benmanes.caffeine.cache.LoadingCache
 *//*
*/
/*

    public void setCacheLoader(CacheLoader<Object, Object> cacheLoader) {
        if (!ObjectUtils.nullSafeEquals(this.cacheLoader, cacheLoader)) {
            this.cacheLoader = cacheLoader;
            refreshKnownCaches();
        }
    }

    *//*

*/
/**
 * Specify whether to accept and convert {@code null} values for all caches
 * in this cache manager.
 * <p>Default is "true", despite Caffeine itself not supporting {@code null} values.
 * An internal holder object will be used to store user-level {@code null}s.
 *//*
*/
/*

    public void setAllowNullValues(boolean allowNullValues) {
        if (this.allowNullValues != allowNullValues) {
            this.allowNullValues = allowNullValues;
            refreshKnownCaches();
        }
    }

    *//*

*/
/**
 * Return whether this cache manager accepts and converts {@code null} values
 * for all of its caches.
 *//*
*/
/*

    public boolean isAllowNullValues() {
        return allowNullValues;
    }


    @Override
    public Collection<String> getCacheNames() {
        return Collections.unmodifiableSet(cacheMap.keySet());
    }

    @Override
    public Cache getCache(String name) {
        Cache cache = cacheMap.get(name);
        if (cache == null && dynamic) {
            synchronized (cacheMap) {
                cache = cacheMap.get(name);
                if (cache == null) {
                    cache = createCaffeineCache(name);
                    cacheMap.put(name, cache);
                }
            }
        }
        return cache;
    }

    *//*

*/
/**
 * Create a new CaffeineCache instance for the specified cache name.
 *
 * @param name the name of the cache
 * @return the Spring CaffeineCache adapter (or a decorator thereof)
 *//*
*/
/*

    protected Cache createCaffeineCache(String name) {
        return new LianfanCaffeineCache(name, createNativeCaffeineCache(name), isAllowNullValues());
    }

    *//*

*/
/**
 * Create a native Caffeine Cache instance for the specified cache name.
 *
 * @param name the name of the cache
 * @return the native Caffeine Cache instance
 *//*
*/
/*

    protected com.github.benmanes.caffeine.cache.Cache<Object, Object> createNativeCaffeineCache(String name) {
        return cacheLoader != null ? cacheBuilder.build(cacheLoader) : cacheBuilder.build();
    }

    private void doSetCaffeine(Caffeine<Object, Object> cacheBuilder) {
        if (!ObjectUtils.nullSafeEquals(this.cacheBuilder, cacheBuilder)) {
            this.cacheBuilder = cacheBuilder;
            refreshKnownCaches();
        }
    }

    *//*

*/
/**
 * Create the known caches again with the current state of this manager.
 *//*
*/
/*

    private void refreshKnownCaches() {
        for (Map.Entry<String, Cache> entry : cacheMap.entrySet()) {
            entry.setValue(createCaffeineCache(entry.getKey()));
        }
    }

}
*//*

        .hg.gama.gamautil.numasutil.util.JsonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching(proxyTargetClass = true, order = OrderConfig.CACHE_ORDER)
public class CacheConfig extends CachingConfigurerSupport {

    @Value("${cache.type:caffeine}")
    private String cacheType;

    @Bean
    @Override
    public CacheManager cacheManager() {
        if ("none".equals(cacheType)) {
            return new NoOpCacheManager();
        } else {
            LianfanCaffeineCacheManager cacheManager = new LianfanCaffeineCacheManager();
            cacheManager.setCaffeine(Caffeine.newBuilder().softValues().expireAfterWrite(1, TimeUnit.HOURS));
            return cacheManager;
        }
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append('.');
            sb.append(method.getName());
            sb.append("^$");
            for (Object obj : params) {
                if (obj == null) {
                    sb.append(":null:");
                } else {
                    if (obj instanceof String) {
                        sb.append(obj);
                    } else if (obj instanceof Integer) {
                        sb.append(obj);
                    } else if (obj instanceof Double) {
                        sb.append(obj);
                    } else if (obj instanceof Float) {
                        sb.append(obj);
                    } else if (obj instanceof BigDecimal) {
                        sb.append(((BigDecimal) obj).toPlainString());
                    } else if (obj instanceof Boolean) {
                        sb.append(obj);
                    } else {
                        sb.append(JsonUtil.toJson(obj));
                    }
                    sb.append(":");
                }
            }
            return sb.toString();
        };
    }


}*/
