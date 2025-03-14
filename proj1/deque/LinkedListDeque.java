package deque;

import java.util.Iterator;
import java.util.Objects;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    class Note {
        private T item;
        private Note previous;
        private Note next;

        Note(T i, Note pre, Note nxt) {
            item = i;
            previous = pre;
            next = nxt;
        }

    }
    private int size;
    private Note sentinel;
    //初始化
    public LinkedListDeque() {
        sentinel = new Note(null, null, null);
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
        size = 0;
    }

    //实现Deque
    @Override
    public boolean isEmpty() {
        return sentinel.next == sentinel || sentinel.previous == sentinel;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void addFirst(T item) {
        Note newnote = new Note(item, null, null);
        newnote.previous = sentinel;
        newnote.next = sentinel.next;
        sentinel.next.previous = newnote;
        sentinel.next = newnote;
        size++;
    }
    @Override
    public void addLast(T item) {
        Note newnote = new Note(item, null, null);
        newnote.next = sentinel;
        newnote.previous = sentinel.previous;
        sentinel.previous.next = newnote;
        sentinel.previous = newnote;
        size++;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next.next.previous = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return item;
    }
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T item = sentinel.previous.item;
        sentinel.previous.previous.next = sentinel;
        sentinel.previous = sentinel.previous.previous;
        size--;
        return item;
    }
    @Override
    public T get(int index) {
        Note midnote = sentinel.next;
        if (size == 0 || index > size) {
            return null;
        } else {
            for (int i = 0; i < index; i++) {
                midnote = midnote.next;
            }
        }
        return midnote.item;
    }
    @Override
    public void printDeque() {
        Note mid = sentinel;
        if (size == 0) {
            System.out.println("null");
        }
        for (int i = 0; i < size; i++) {
            System.out.println(mid.next.item);
            mid = mid.next;
        }
    }
    //一个以递归实现的get()
    public T getRecursive(int index) {
        Note mid = sentinel;
        return getRecursive(mid.next, index);
    }
    private T getRecursive(Note n, int index) {
        if (index == 0) {
            return n.item;
        }
        n = n.next;
        index--;
        return getRecursive(n, index);
    }
    //实现Iterator
    private class LinkedListDequeIterator implements Iterator<T> {
        private int pos;
        LinkedListDequeIterator() {
            pos = 0;
        }
        @Override
        public boolean hasNext() {
            return pos < size;
        }
        @Override
        public T next() {
            T item = get(pos);
            pos += 1;
            return item;
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> mid = (Deque<T>) o;
        if (mid.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < mid.size(); i++) {
            return mid.get(i).equals(this.get(i));
        }
        return true;
    }
}
