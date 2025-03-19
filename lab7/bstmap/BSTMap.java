package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private int size;
    private BSTNote root;

    private class BSTNote {
        K key;
        V val;
        BSTNote left;
        BSTNote right;

        BSTNote(K key, V val) {
            this.key = key;
            this.val = val;
            this.left = null;
            this.right = null;
        }

        boolean is_leaf(){
            return left == null && right == null;
        }

    }

    BSTMap() {
        root = null;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("get() get a null key");
        }
        return containsKey(root,key);
    }
    private boolean containsKey(BSTNote note, K key) {
        if (note == null) {
            return false;
        }
        if (note.key.equals(key)) {
            return true;
        }
        if (note.is_leaf()) {
            return false;
        }
        return containsKey(note.left, key) || containsKey(note.right, key);
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("get() get a null key");
        }
        return get(root, key);
    }
    private V get(BSTNote note, K key) {
        if (note == null) {
            return null;
        }
        int mid = key.compareTo(note.key);
        if (mid < 0) {
            return get(note.left, key);
        } else if (mid > 0) {
            return get(note.right, key);
        } else {
            return note.val;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("put() get a null key");
        }
        if (root == null) {
            root = new BSTNote(key, value);
        } else {
            put(root, key, value);
        }

        size++;
    }
    private void put(BSTNote note, K key, V value) {
        int mid = key.compareTo(note.key);
        if (mid < 0) {
            if (note.left == null) {
                note.left = new BSTNote(key, value);
            } else {
                put(note.left, key, value);
            }
        } else if (mid > 0) {
            if (note.right == null) {
                note.right = new BSTNote(key, value);
            } else {
                put(note.right, key, value);
            }
        }
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

}
