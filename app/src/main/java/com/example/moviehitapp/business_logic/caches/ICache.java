package com.example.moviehitapp.business_logic.caches;

import com.example.moviehitapp.annotations.Cache;

@Cache
public interface ICache<K , V> {
    void add(K k , V v);
    V get(K k);
    void clear();
}
