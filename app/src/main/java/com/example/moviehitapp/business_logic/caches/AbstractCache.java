package com.example.moviehitapp.business_logic.caches;

import java.util.HashMap;
import java.util.Map;

public class AbstractCache<K , V> implements ICache<K , V>{

    protected Map<K , V> cache;

    protected AbstractCache() {
        cache = new HashMap<>();
    }

    protected AbstractCache(Map<K , V> cache) {
        this.cache = cache;
    }

    @Override
    public void add(K k , V v) {
        cache.put(k, v);
    }

    @Override
    public V get(K k) {
        return cache.get(k);
    }

    @Override
    public void clear() {
        cache.clear();
    }
}
