package deque;

import java.util.Iterator;
import java.util.Objects;

public interface Deque <T>{
    void addFirst(T item);
    void addLast(T item);
    boolean isEmpty();
    int size();
    void printDeque();
    T removeFirst();
    T removeLast();
    T get(int index);
    Iterator<T> iterator();
    boolean equals(Object n);
}
