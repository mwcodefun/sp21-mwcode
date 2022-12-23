package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    @Override
    public void clear() {
        this.buckets = createTable(this.buckets.length);
        this.size = 0;
    }

    private Node find(K k){
        int p = hash(k) % buckets.length;
        Collection<Node> bucket = buckets[p];
        if (bucket == null){
            return null;
        }
        for (Node node : bucket){
            if (node.key.equals(k)){
                return node;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return find(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = find(key);
        return node == null ? null : node.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        Node node = find(key);
        if (node != null){
            node.value = value;
            return;
        }

        if (size >= buckets.length * loadFactor){
            resize(buckets.length * 2);
        }

        Node newNode = createNode(key,value);
        int p = hash(key) % buckets.length;
        Collection<Node> bucket = buckets[p];
        if (bucket == null){
            Collection<Node> newBucket = createBucket();
            newBucket.add(newNode);
            buckets[p] = newBucket;
        }else{
            bucket.add(newNode);
        }
        size++;
    }

    private int hash(K k){
        return (k.hashCode() & 0x7fffffff);
    }

    public void resize(int newsize){
        Collection<Node>[] newbuckets = createTable(newsize);
        for (Collection<Node> bucket : buckets) {
            if (bucket == null){
                continue;
            }
            for (Node node : bucket) {
                int p = hash(node.key) % newsize;
                Collection<Node> newbucket = newbuckets[p];
                if (newbucket == null){
                    newbucket = createBucket();
                    newbuckets[p] = newbucket;
                }
                newbucket.add(node);
            }
        }
    }

    @Override
    public Set<K> keySet() {
        HashSet<K> keySet = new HashSet<>();
        for (Collection<Node> bucket : buckets) {
            if (bucket == null){
                continue;
            }
            for (Node node : bucket) {
                keySet.add(node.key);
            }
        }
        return keySet;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        HashSet<K> keySet = new HashSet<>();
        for (Collection<Node> bucket : buckets) {
            if (bucket == null){
                continue;
            }
            for (Node node : bucket) {
                keySet.add(node.key);
            }
        }
        return keySet.iterator();
    }

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int size;
    private int capacity;
    private double loadFactor = 0.75;
    /** Constructors */
    public MyHashMap() {
        this(16);
    }

    public MyHashMap(int initialSize) {
        this.buckets = new Collection[initialSize];
        this.size = 0;
        this.capacity = 16;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this(initialSize);
        this.loadFactor = maxLoad;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

}
