package hashmap;

import java.util.*;

import static org.junit.Assert.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author SuMeteor
 */
public class MyHashMap<K, V> implements Map61B<K, V> {


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
        Node() {
            key = null;
            value = null;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private HashSet<K> keySet;
    int size;
    double loadFactor;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.size = 0;
        this.loadFactor = maxLoad;
        keySet = new HashSet<>();
        buckets = new Collection[initialSize];
        for (int i = 0; i < initialSize; i++) {
            buckets[i] = createBucket();
        }
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
        return null;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    @Override
    public void clear() {
        buckets = null;
        keySet = null;
        size = 0;
        loadFactor = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (keySet == null) {
            return false;
        } else {
            return keySet.contains(key);
        }
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    private Node getNode(K key) {
        if (size == 0) {
            return null;
        }
        int index = Math.floorMod(key.hashCode(), buckets.length);
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                return node;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        Node node = getNode(key);
        if (node != null) {
            node.value = value;
        } else {
            int index = Math.floorMod(key.hashCode(), buckets.length);
            buckets[index].add(createNode(key, value));
            size++;
            keySet.add(key);
            if ((double)size / (double)buckets.length > loadFactor) {
                resize(buckets.length * 2);
            }
        }
    }

    private void resize(int capacity) {
        Collection<Node>[] resize = new Collection[capacity];
        for (int i = 0; i < capacity; i++) {
            resize[i] = createBucket();
        }
        for (int i = 0; i < buckets.length; i++) {
            for (Node node : buckets[i]) {
                int index = Math.floorMod(node.key.hashCode(), capacity);
                resize[index].add(node);
            }
        }
        buckets = resize;
    }

    @Override
    public Set<K> keySet() {
        return keySet;
    }

    @Override
    public V remove(K key) {
        int index = Math.floorMod(key.hashCode(), buckets.length);
        Node node = getNode(key);
        if (node == null) {
            return null;
        } else {
            V value = node.value;
            buckets[index].remove(node);
            keySet.remove(key);
            return value;
        }
    }

    @Override
    public V remove(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            return null;
        } else {
            V val = node.value;
            if (val == value) {
                return remove(key);
            } else {
                return null;
            }
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new MHMIterator();
    }
    private class MHMIterator implements Iterator {
        Queue<Node> queue;

        MHMIterator() {
            queue = new LinkedList<>();
            for (Collection<Node> items : buckets) {
                queue.addAll(items);
            }
        }

        @Override
        public boolean hasNext() {
            return queue.isEmpty();
        }

        @Override
        public Node next() {
            return queue.poll();
        }
    }
    public static void main(String[] args) {
        MyHashMap<String, String> q = new MyHashMap<>();
        q.put("c", "a");
        q.put("b", "a");
        q.put("a", "a");
        q.put("d", "a");
        q.put("e", "a"); // a b c d e
        q.remove("c");
        boolean x;
        x = q.containsKey("c");
        x = q.containsKey("a");
        x = q.containsKey("b");
        x = q.containsKey("d");
        x = q.containsKey("e");
    }
}
