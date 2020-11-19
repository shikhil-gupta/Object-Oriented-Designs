package com.example.cache.strategy;

public interface IEvictionStrategy<V> {

    String evict();

    void addToTop(String key, V value);

    void delete(String key);
}
