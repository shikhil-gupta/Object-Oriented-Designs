package com.example.cache.Interface;

public interface ICache<V> {

    void put(final String key, final V value);

    V get(final String key);

    V remove(final String key);
}
