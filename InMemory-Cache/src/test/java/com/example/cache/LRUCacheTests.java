package com.example.cache;

import com.example.cache.Impl.CacheImpl;
import com.example.cache.pojo.Node;
import com.example.cache.strategy.IEvictionStrategy;
import com.example.cache.strategy.LeastRecentlyUsedStrategyImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class LRUCacheTests {
    @Test
    void addKeyToCacheTest() {
        IEvictionStrategy<Integer> evictionStrategy = new LeastRecentlyUsedStrategyImpl<Integer>();
        CacheImpl<Integer> cache = new CacheImpl<Integer>(5, evictionStrategy);

        cache.put("hello1", 1);
        Assert.isTrue(cache.getEmptySlots() == 4);
    }

    @Test
    void getKeyFromCacheTest() {

        LeastRecentlyUsedStrategyImpl leastRecentlyUsedStrategy = new LeastRecentlyUsedStrategyImpl<Integer>();
        CacheImpl<Integer> cache = new CacheImpl<Integer>(3, leastRecentlyUsedStrategy);
        cache.put("hello1", 1);
        cache.put("hello2", 2);
        cache.put("hello3", 3);

        Assert.isTrue(cache.getEmptySlots() == 0);
        Assert.isTrue(leastRecentlyUsedStrategy.getCurrentCapacity() == 3);

        Node<Integer> head = leastRecentlyUsedStrategy.getHead().getRight();
        Node<Integer> tail = leastRecentlyUsedStrategy.getTail();
        Assert.isTrue(head.getKey().equals("hello3"));
        head = head.getRight();
        Assert.isTrue(head.getKey().equals("hello2"));
        head = head.getRight();
        Assert.isTrue(head.getKey().equals("hello1"));
    }

    @Test
    void checkFullCapacityTest() {

    }

    @Test
    void checkKeysOrder() {

    }
}
