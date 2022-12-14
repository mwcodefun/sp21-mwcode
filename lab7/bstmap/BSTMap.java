package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements  Map61B<K,V>{

    private Node<K,V> root;

    private int size = 0;

    static class Node<K,V> {
        Node left;
        Node right;
        K key;
        V val;
    }

    private Node<K,V> find(Node<K,V> node,K k){
        if (node == null){
            return null;
        }
        if (node.key.equals(k)){
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

     private Node put(Node<K,V> node,K k,V v){
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
    public void printInOrder(){
        if(root == null){
            return;
        }
        printInOrder(root);
    }

    private void printInOrder(Node<K,V> node){
        if(node == null){
            return;
        }
        printInOrder(node.left);
        System.out.println(node.key + " " + node.val);
        printInOrder(node.right);
    }

    @Override
    public void put(K key, V value) {
        if (root == null){
            root = new Node<>();
            root.key = key;
            root.val = value;
            size++;
            return;
        }
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
