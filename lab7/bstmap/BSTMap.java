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
        BSTNote note = getKeyNote(root, key);
        BSTNote preNote = getPreNote(root, key);
        V val = note.val;
        boolean left = true;
        if (preNote.left == note) {
            left = true;
        } else if (preNote.right == note) {
            left = false;
        }
        removeNote(preNote, note, left);
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
        if (note.left.key == key || note.right.key == key) {
            return note;
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
                    BSTNote newNote = getNearistLeftNote(note);
                    BSTNote preNewNote = getPreNote(root, newNote.key);
                    preNote.left = newNote;
                    newNote.left = note.left;
                    newNote.right = note.right;
                    if (newNote.left != null) {
                        preNewNote.right = newNote.left;
                    }
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
                    BSTNote newNote = getNearistLeftNote(note);
                    BSTNote preNewNote = getPreNote(root, newNote.key);
                    preNote.right = newNote;
                    newNote.left = note.left;
                    newNote.right = note.right;
                    if (newNote.left != null) {
                        preNewNote.right = newNote.left;
                    }
            }
        }
    }
    public BSTNote getNearistLeftNote(BSTNote note) {
        note = note.left;
        if (note.right != null) {
            return getNearistLeftNote(note);
        } else {
            return note.right;
        }
    }

    @Override
    public V remove(K key, V value) {
        BSTNote note = getKeyNote(root, key);
        BSTNote preNote = getPreNote(root, key);
        V val = note.val;
        if (val == value) {
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
    }

    public void printInOrder(){
        System.out.println("{keys,Values}");
        inOrder(root);
    }
    private void inOrder(BSTNote note){
        if (note == null) {
            return;
        }
        inOrder(note.left);
        System.out.println("{" + note.key + "," + note.val + "}");
        inOrder(note.right);
    }


}
