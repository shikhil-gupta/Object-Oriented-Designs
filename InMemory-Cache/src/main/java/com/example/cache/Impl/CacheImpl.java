package com.example.cache.Impl;

import com.example.cache.Interface.ICache;
import com.example.cache.strategy.IEvictionStrategy;

import java.util.HashMap;

public class CacheImpl<V> implements ICache<V> {

    private int emptySlots;
    private HashMap<String,V> map;
    private IEvictionStrategy evictionStrategy;

    public CacheImpl(final int capacity, final IEvictionStrategy evictionStrategy) {
        this.emptySlots = capacity;
        this.map = new HashMap<>();
        this.evictionStrategy = evictionStrategy;
    }

    @Override
    public void put(final String key, final V value) {

        if(map.containsKey(key)) {
            map.remove(key);
            evictionStrategy.delete(key);
            emptySlots++;
        }

        if(emptySlots > 0) {
            this.map.put(key, value);
            this.evictionStrategy.addToTop(key, value);
            emptySlots--;
        } else {
            this.evictionStrategy.evict();
            this.map.put(key, value);
            this.evictionStrategy.addToTop(key, value);
        }
    }

    @Override
    public V get(final String  key) {

        if(!map.containsKey(key)) {
            return null;
        }

        V value = map.get(key);
        this.evictionStrategy.addToTop(key,value);
        return value;
    }

    @Override
    public V remove(final String key) {

        if(!map.containsKey(key)) {
            return null;
        }

        V value = map.remove(key);
        evictionStrategy.delete(key);
        emptySlots++;
        return value;
    }
}
