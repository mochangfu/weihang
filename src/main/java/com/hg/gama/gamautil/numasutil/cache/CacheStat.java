package com.hg.gama.gamautil.numasutil.cache;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CacheStat {

    private static Map<String, CacheStatInfo> cacheStatInfoMap = Maps.newConcurrentMap();

    private static String getCacheName(String cacheName, Object key) {
        if (key instanceof String) {
            int idx = ((String) key).indexOf("^$");
            if (idx > -1) {
                return cacheName + "^^" + ((String) key).substring(0, idx);
            } else {
                return cacheName;
            }
        } else {
            return cacheName;
        }
    }

    public static void addHit(String cacheName, Object key) {
        cacheName = getCacheName(cacheName, key);
        CacheStatInfo cacheStatInfo = cacheStatInfoMap.get(cacheName);
        if (cacheStatInfo == null) {
            cacheStatInfo = new CacheStatInfo();
            cacheStatInfo.setCacheName(cacheName);
            cacheStatInfoMap.put(cacheName, cacheStatInfo);
        }
        cacheStatInfo.total.incrementAndGet();
        cacheStatInfo.hit.incrementAndGet();
    }

    public static void addMismatch(String cacheName, Object key) {
        cacheName = getCacheName(cacheName, key);
        CacheStatInfo cacheStatInfo = cacheStatInfoMap.get(cacheName);
        if (cacheStatInfo == null) {
            cacheStatInfo = new CacheStatInfo();
            cacheStatInfo.setCacheName(cacheName);
            cacheStatInfoMap.put(cacheName, cacheStatInfo);
        }
        cacheStatInfo.total.incrementAndGet();
        cacheStatInfo.miss.incrementAndGet();
    }


    public static void clear() {
        cacheStatInfoMap.values().forEach(c -> {
            c.total.set(0);
            c.hit.set(0);
            c.miss.set(0);
        });
    }

    public static class CacheStatInfo {
        private String cacheName;
        private AtomicInteger total = new AtomicInteger();
        private AtomicInteger hit = new AtomicInteger();
        private AtomicInteger miss = new AtomicInteger();

        public String getCacheName() {
            return cacheName;
        }

        public CacheStatInfo setCacheName(String cacheName) {
            this.cacheName = cacheName;
            return this;
        }

        public AtomicInteger getTotal() {
            return total;
        }

        public CacheStatInfo setTotal(AtomicInteger total) {
            this.total = total;
            return this;
        }

        public AtomicInteger getHit() {
            return hit;
        }

        public CacheStatInfo setHit(AtomicInteger hit) {
            this.hit = hit;
            return this;
        }

        public AtomicInteger getMiss() {
            return miss;
        }

        public CacheStatInfo setMiss(AtomicInteger miss) {
            this.miss = miss;
            return this;
        }
    }

}
