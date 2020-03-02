/*
package com.hg.gama.gamautil.numasutil.cache;

import com.github.benmanes.caffeine.cache.LoadingCache;
import com.hg.gama.gamautil.numasutil.util.FastSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.Callable;
import java.util.function.Function;

public class LianfanCaffeineCache extends AbstractValueAdaptingCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(LianfanCaffeineCache.class);

    private final String name;

    private final com.github.benmanes.caffeine.cache.Cache<Object, Object> cache;


    */
/**
     * Create a {@link LianfanCaffeineCache} instance with the specified name and the
     * given internal {@link com.github.benmanes.caffeine.cache.Cache} to use.
     *
     * @param name  the name of the cache
     * @param cache the backing Caffeine Cache instance
     *//*

    public LianfanCaffeineCache(String name, com.github.benmanes.caffeine.cache.Cache<Object, Object> cache) {
        this(name, cache, true);
    }

    */
/**
     * Create a {@link LianfanCaffeineCache} instance with the specified name and the
     * given internal {@link com.github.benmanes.caffeine.cache.Cache} to use.
     *
     * @param name            the name of the cache
     * @param cache           the backing Caffeine Cache instance
     * @param allowNullValues whether to accept and convert {@code null}
     *                        values for this cache
     *//*

    public LianfanCaffeineCache(String name, com.github.benmanes.caffeine.cache.Cache<Object, Object> cache,
            boolean allowNullValues) {

        super(allowNullValues);
        Assert.notNull(name, "Name must not be null");
        Assert.notNull(cache, "Cache must not be null");
        this.name = name;
        this.cache = cache;
    }


    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final com.github.benmanes.caffeine.cache.Cache<Object, Object> getNativeCache() {
        return cache;
    }

    @Override
    public ValueWrapper get(Object key) {
        if (cache instanceof com.github.benmanes.caffeine.cache.LoadingCache) {
            Object value = ((LoadingCache<Object, Object>) cache).get(key);
            return toValueWrapper(value);
        }
        return super.get(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key, final Callable<T> valueLoader) {
        Object result = fromStoreValue(cache.get(key, new LoadFunction(valueLoader)));
        return (T) copy(name, key, result);
    }

    @Override
    protected Object lookup(Object key) {
        Object result = cache.getIfPresent(key);
        if (result == null) {
            CacheStat.addMismatch(name, key);
        } else {
            CacheStat.addHit(name, key);
        }
        return copy(name, key, result);
    }

    @Override
    public void put(Object key, Object value) {
        cache.put(key, toStoreValue(newCopy(name, key, value)));
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, final Object value) {
        PutIfAbsentFunction callable = new PutIfAbsentFunction(value);
        Object result = cache.get(key, callable);
        return (callable.called ? null : toValueWrapper(result));
    }

    @Override
    public void evict(Object key) {
        cache.invalidate(key);
    }

    @Override
    public void clear() {
        cache.invalidateAll();
    }

    @SuppressWarnings("unchecked")
    private static <T> T copy(String cacheName, Object key, Object t) {
        if (t == null) {
            return null;
        }
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
            // 不在SpringMVC的上下文环境中，直接返回复制对象
            return (T) FastSerializer.copy(t, t.getClass());
        } else {
            String cacheKey = cacheName + "_" + key.hashCode() + "_" + String.valueOf(t.hashCode());
            Object val = attrs.getAttribute(cacheKey, RequestAttributes.SCOPE_REQUEST);
            if (val == null) {
                T copyVal = (T) FastSerializer.copy(t, t.getClass());
                attrs.setAttribute(cacheKey, copyVal, RequestAttributes.SCOPE_REQUEST);
                return copyVal;
            } else {
                return (T) val;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T newCopy(String cacheName, Object key, Object t) {
        if (t == null) {
            return null;
        }
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
            // 不在SpringMVC的上下文环境中，直接返回复制对象
            return (T) FastSerializer.copy(t, t.getClass());
        } else {
            String cacheKey = cacheName + "_" + key.hashCode() + "_" + String.valueOf(t.hashCode());
            T copyVal = (T) FastSerializer.copy(t, t.getClass());
            attrs.setAttribute(cacheKey, copyVal, RequestAttributes.SCOPE_REQUEST);
            return copyVal;
        }
    }


    private class PutIfAbsentFunction implements Function<Object, Object> {

        private final Object value;

        private boolean called;

        PutIfAbsentFunction(Object value) {
            this.value = value;
        }

        @Override
        public Object apply(Object key) {
            called = true;
            return toStoreValue(newCopy(name, key, value));
        }
    }


    private class LoadFunction implements Function<Object, Object> {

        private final Callable<?> valueLoader;

        LoadFunction(Callable<?> valueLoader) {
            this.valueLoader = valueLoader;
        }

        @Override
        public Object apply(Object o) {
            try {
                return toStoreValue(valueLoader.call());
            } catch (Exception ex) {
                throw new ValueRetrievalException(o, valueLoader, ex);
            }
        }
    }

}
*/
