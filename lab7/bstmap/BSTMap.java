package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable,V> implements  Map61B<K,V>{

    Node<K,V> root;

    int size = 0;

    static class Node<K,V> {
        Node left;
        Node right;
        K key;
        V val;
    }

    public Node<K,V> find(Node<K,V> node,K k){
        if (node == null){
            return null;
        }
        if (node.key == k){
            return node;
        }
        if (node.key.compareTo(k) >= 0){
            return find(node.left,k);
        }else{
            return find(node.right,k);
        }
    }

    @Override
    public void clear() {
        this.size = 0;
        this.root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return find(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node<K, V> kvNode = find(root, key);
        if (kvNode == null){
            return null;
        }
        return kvNode.val;
    }

    @Override
    public int size() {
        return size;
    }

    public Node put(Node<K,V> node,K k,V v){
        if (node == null){
            Node<K,V> newNode = new Node<>();
            newNode.key = k;
            newNode.val = v;
            size++;
            return newNode;
        }
        if (node.key.compareTo(k) >= 0){
            node.left = put(node.left,k,v);
        }else{
            node.right = put(node.right,k,v);
        }
        return node;
    }

    @Override
    public void put(K key, V value) {
        this.put(root,key,value);
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public V remove(K key, V value) {
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }
}
