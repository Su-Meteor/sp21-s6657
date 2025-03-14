package deque;

import java.util.Iterator;
import java.util.Objects;

public interface Deque <T>{
    public void addFirst(T item);
    public void addLast(T item);
    public boolean isEmpty();
    public int size();
    public void printDeque();
    public T removeFirst();
    public T removeLast();
    public T get(int index);
    public Iterator<T> iterator();
    public boolean equals(Object n);
}
