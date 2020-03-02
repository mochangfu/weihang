package com.hg.gama.gamautil.numasutil.cache;

@FunctionalInterface
public interface CacheLoadFunction<T> {
    T run();
}