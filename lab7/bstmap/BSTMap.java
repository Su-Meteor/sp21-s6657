package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private int size;
    private BSTNote root;
    private HashSet<K> keySet;

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

        boolean is_leaf() {
            return left == null && right == null;
        }
        int getChildNum() {
            int childNum = 0;
            if (this.left != null) {
                childNum ++;
            }
            if (this.right != null) {
                childNum ++;
            }
            return childNum;
        }

    }

    public BSTMap() {
        root = null;
        keySet = new HashSet<>();
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
        keySet.add(key);
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
        return keySet;
    }

    @Override
    public V remove(K key) {
        BSTNote note = getKeyNote(root, key);
        V val = note.val;
        K k = note.key;
        if (note == null) {
            return null;
        }
        if (note == root) {
            removeRoot();
            keySet.remove(k);
            size--;
            return val;
        }
        BSTNote preNote = getPreNote(root, key);
        boolean left = true;
        if (preNote.left == note) {
            left = true;
        } else if (preNote.right == note) {
            left = false;
        }
        removeNote(preNote, note, left);
        keySet.remove(k);
        size--;
        return val;
    }

    private BSTNote getKeyNote(BSTNote note,K key) {
        if (note == null) {
            return null;
        }
        if (key.compareTo(note.key) < 0) {
            return getKeyNote(note.left, key);
        } else if (key.compareTo(note.key) > 0) {
            return getKeyNote(note.right, key);
        } else {
            return note;
        }
    }
    private BSTNote getPreNote(BSTNote note, K key) {
        if (note == null) {
            return null;
        }
        if (note.left != null) {
            if (key.equals(note.left.key)) {
                return note;
            }
        }
        if (note.right != null) {
            if (key.equals(note.right.key)) {
                return note;
            }
        }
        if (key.compareTo(note.key) < 0) {
            return getPreNote(note.left, key);
        } else if (key.compareTo(note.key) > 0) {
            return getPreNote(note.right, key);
        } else {
            return null;
        }
    }
    private void removeNote(BSTNote preNote, BSTNote note, boolean left) {
        if (left) {
            switch (note.getChildNum()) {
                case 0:
                    preNote.left = null;
                    break;
                case 1:
                    if (note.left == null) {
                        preNote.left = note.right;
                    } else if (note.right == null){
                        preNote.left = note.left;
                    }
                    break;
                case 2:
                    BSTNote newNote = getClosestLeftNote(note.left);
                    BSTNote preNewNote = getPreNote(root, newNote.key);
                    preNote.left = newNote;
                    preNewNote.right = newNote.left;
                    newNote.left = note.left;
                    newNote.right = note.right;
                    break;
                default:
                    return;
            }
        } else {
            switch (note.getChildNum()) {
                case 0:
                    preNote.right = null;
                    break;
                case 1:
                    if (note.left == null) {
                        preNote.right = note.right;
                    } else if (note.right == null) {
                        preNote.right = note.left;
                    }
                    break;
                case 2:
                    BSTNote newNote = getClosestLeftNote(note.left);
                    BSTNote preNewNote = getPreNote(root, newNote.key);
                    preNote.left = newNote;
                    preNewNote.right = newNote.left;
                    newNote.left = note.left;
                    newNote.right = note.right;
                    break;
                default :
                    return;
            }
        }
    }
    private void removeRoot() {
        switch (root.getChildNum()) {
            case 0:
                root = null;
                break;
            case 1:
                if (root.right == null) {
                    root = root.left;
                } else if (root.left == null) {
                    root = root.right;
                }
                break;
            case 2:
                BSTNote newNote = getClosestLeftNote(root.left);
                BSTNote preNewNote = getPreNote(root,newNote.key);
                if (preNewNote == root) {
                    root.left = root.left.left;
                }
                newNote.left = root.left;
                newNote.right = root.right;
                root = newNote;
                preNewNote.right = null;
                break;
            default:
                return;
        }
    }
    private BSTNote getClosestLeftNote(BSTNote note) {
        if (note.right != null) {
            return getClosestLeftNote(note.right);
        } else {
            return note;
        }
    }
    private BSTNote getClosestRightNote(BSTNote note) {
        if (note.left != null) {
            return getClosestRightNote(note.left);
        } else {
            return note;
        }
    }

    @Override
    public V remove(K key, V value) {
        BSTNote note = getKeyNote(root, key);
        if (note == null) {
            return null;
        }
        BSTNote preNote = getPreNote(root, key);
        V val = note.val;
        if (val != value) {
            return null;
        }
        boolean left = true;
        if (preNote.left == note) {
            left = true;
        } else if (preNote.right == note) {
            left = false;
        }
        removeNote(preNote, note, left);
        return val;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
//        return new BSTMapIterator();
    }
    private class BSTMapIterator<T> implements Iterator<T> {
        int pos;
        BSTMapIterator() {
            pos = 0;
        }
        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            return null;
        }
    }

    public void printInOrder(){
        System.out.println("{keys,Values}");
        printInOrder(root);
    }
    private void printInOrder(BSTNote note){
        if (note == null) {
            return;
        }
        printInOrder(note.left);
        System.out.println("{" + note.key + "," + note.val + "}");
        printInOrder(note.right);
    }

}
