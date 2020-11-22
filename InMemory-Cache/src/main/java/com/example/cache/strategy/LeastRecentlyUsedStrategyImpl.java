package com.example.cache.strategy;

import com.example.cache.pojo.Node;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class LeastRecentlyUsedStrategyImpl<V> implements IEvictionStrategy<V>
{

    private HashMap<String, Node> keyToNodeMap;
    private Node head;
    private Node tail;

    public LeastRecentlyUsedStrategyImpl() {
        this.keyToNodeMap = new HashMap<>();
        this.head = new Node("", null);
        this.tail = new Node("", null);
        head.setRight(tail);
        tail.setLeft(head);
    }

    @Override
    public String evict() {

        if(head.getRight() == tail) {
            return "";
        }

        Node node = keyToNodeMap.get(tail.getLeft().getKey());
        delete(node.getKey());
        return node.getKey();
    }

    @Override
    public void addToTop(String key, V value)
    {
        Node node = null;
        delete(key);

        node = new Node(key, value);
        Node right = head.getRight();
        head.setRight(node);
        node.setRight(right);
        node.setLeft(head);
        right.setLeft(node);
        keyToNodeMap.put(key, node);
    }

    @Override
    public void delete(String key) {
        if(!keyToNodeMap.containsKey(key)) {
            return;
        }

        Node node = keyToNodeMap.remove(key);
        node.getLeft().setRight(node.getRight());
        node.getRight().setLeft(node.getLeft());
        node.setLeft(null);
        node.setRight(null);
    }
}
