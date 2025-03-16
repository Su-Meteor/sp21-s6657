package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int firstIndex = 0;
    private int lastIndex = 7;
    private static final int MIN_LENGTH = 8;
    private int length = MIN_LENGTH;

    //初始化
    public ArrayDeque() {
        items = (T[]) new Object[length];
        size = 0;
    }

    //调整函数
    private int adjustIndex(int index, boolean forward) {
        if (forward) {
            index--;
            if (index < 0) {
                index = length - 1;
            }
        } else {
            index++;
            if (index > length - 1) {
                index = 0;
            }
        }
        return index;
    }
    private void adjustLength(int newl, boolean extend) {
        T[] a = (T[]) new Object[newl];
        int idx = firstIndex;
        //copy array
        if (extend) {
            for (int i = 0; i < size - 1; i++) {
                a[i] = items[idx];
                idx = adjustIndex(idx, false);
                firstIndex = 0;
                lastIndex = size - 2;
            }
        } else {
            for (int i = 0; i < size; i++) {
                a[i] = items[idx];
                idx = adjustIndex(idx, false);
                firstIndex = 0;
                lastIndex = size - 1;
            }
        }
        items = a;
    }
    private void adjustFirstIndex(boolean forward) {
        firstIndex = adjustIndex(firstIndex, forward);
    }
    private void adjustLastIndex(boolean forward) {
        lastIndex = adjustIndex(lastIndex, forward);
    }

    //重写方法
    @Override
    public void addFirst(T item) {
        size++;
        if (size > length) {
            adjustLength(length * 4, true);
            length = length * 4;
        }
        adjustFirstIndex(true);
        items[firstIndex] = item;
    }

    @Override
    public void addLast(T item) {
        size++;
        if (size > length) {
            adjustLength(length * 4, true);
            length = length * 4;
        }
        adjustLastIndex(false);
        items[lastIndex] = item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        //
        int idx = firstIndex;
        for (int i = 0; i < size; i++) {
            System.out.println(get(idx));
            idx = adjustIndex(idx, false);
        }
    }

    @Override
    public T removeFirst() {
        if (size <= 0) {
            return null;
        }
        T mid = items[firstIndex];
        size--;
        items[firstIndex] = null;
        adjustFirstIndex(false);
        if (size <= length / 4 && length != MIN_LENGTH) {
            adjustLength(length / 4, false);
            length = length / 4;
        }
        return mid;
    }

    @Override
    public T removeLast() {
        if (size <= 0) {
            return null;
        }
        T mid = items[lastIndex];
        size--;
        items[lastIndex] = null;
        adjustLastIndex(true);
        if (size < 0) {
            return null;
        }
        if (size <= length / 4 && length != MIN_LENGTH) {
            adjustLength(length / 4, false);
            length = length / 4;
        }
        return mid;
    }

    @Override
    public T get(int index) {
        T item;
        int idx = firstIndex;
        for (int i = 0; i < index; i++) {
            idx = adjustIndex(idx, false);
        }
        item = items[idx];
        return item;
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int pos;

        ArrayDequeIterator() {
            pos = 0;
        }
        @Override
        public boolean hasNext() {
            return pos < size - 1;
        }
        @Override
        public T next() {
            T item = get(pos);
            pos = pos + 1;
            return item;
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
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
            if (!(mid.get(i).equals(this.get(i)))) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
//        ArrayDeque<Integer> ard = new ArrayDeque<>();
//        Iterator<Integer> seer = ard.iterator();
//        Iterator<Integer> seer1 = null;
//        for (int i = 0; i < 10; i++) {
//            ard.addLast(i);
//        }
//        int y;
//        for (int i = 0; i < 100; i++) {
//            seer.next();
//        }
//        boolean x = seer.hasNext();
        ArrayDeque<Integer> ard = new ArrayDeque<>();
        for (int i = 0; i < 10;i++){
            ard.addLast(i);
        }
        Iterator<Integer> seer = ard.iterator();
        boolean y;
        for (int i = 0; i < 9; i++) {
            y = i == seer.next();
        }
        boolean x = seer.hasNext();
        return;
    }
}
