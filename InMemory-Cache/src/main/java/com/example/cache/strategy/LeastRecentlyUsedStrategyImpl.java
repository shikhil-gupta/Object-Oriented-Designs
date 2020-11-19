package com.example.cache.strategy;

import com.example.cache.pojo.Node;

import java.util.HashMap;

public class LeastRecentlyUsedStrategyImpl<V> implements IEvictionStrategy<V>
{

    private HashMap<String, Node> keyToNodeMap;
    private Node head;
    private Node tail;
    private Integer currentCapacity;

    public LeastRecentlyUsedStrategyImpl()
    {
        this.keyToNodeMap = new HashMap<>();
        this.head = new Node("", null);
        this.tail = new Node("", null);
        head.setRight(tail);
        tail.setLeft(head);
        currentCapacity = 0;
    }

    @Override
    public String evict() {

        if(currentCapacity == 0) {
            return "";
        }

        Node node  = keyToNodeMap.get(tail.getLeft().getKey());
        keyToNodeMap.remove(node.getKey());
        node.getLeft().setRight(tail);
        tail.setLeft(node.getLeft());
        currentCapacity--;
        return node.getKey();
    }

    @Override
    public void addToTop(String key, V value) {

        Node node = null;
        delete(key);
        if(keyToNodeMap.containsKey(key)) {
            node = keyToNodeMap.get(key);
            node.setValue(value);
        } else {
            node = new Node(key, value);
        }

        Node right = head.getRight();
        head.setRight(node);
        node.setRight(right);
        node.setLeft(head);
        right.setLeft(node);
        keyToNodeMap.put(key, node);
        currentCapacity++;
    }

    @Override
    public void delete(String key) {

        if(currentCapacity == 0 || !keyToNodeMap.containsKey(key)) {
            return;
        }

        Node node = keyToNodeMap.remove(key);
        node.getLeft().setRight(node.getRight());
        node.getRight().setLeft(node.getLeft());
        node.setLeft(null);
        node.setRight(null);
        currentCapacity--;
    }
}
