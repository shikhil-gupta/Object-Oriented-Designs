package com.example.cache.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node<V>
{
    private Node left;
    private Node right;

    private String key;
    private V value;

    public Node(String key, V value) {
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
    }
}
