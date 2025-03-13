package deque;

import java.util.Iterator;

public class LinkedListDeque<Item>implements Deque<Item>,Iterable<Item> {
    private class Note{
        public Item item;
        public Note previous;
        public Note next;

        public Note (Item i, Note pre, Note nxt){
            item = i;
            previous = pre;
            next = nxt;
        }

    }
    private int size;
    private Note sentinel;
    //初始化
    public LinkedListDeque(){
        sentinel = new Note(null,null,null);
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
        size = 0;
    }
    public LinkedListDeque(Item x){
        sentinel = new Note(null, null, null);
        sentinel.next = new Note(x, sentinel, sentinel);
        size = 1;
    }

    //实现Deque
    @Override
    public boolean isEmpty(){
        return sentinel.next == sentinel || sentinel.previous == sentinel;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void addFirst(Item item){
        Note newnote = new Note(item,null,null);
        newnote.previous = sentinel;
        newnote.next = sentinel.next;
        sentinel.next.previous = newnote;
        sentinel.next = newnote;
        size++;
    }
    @Override
    public void addLast(Item item){
        Note newnote = new Note(item,null,null);
        newnote.next = sentinel;
        newnote.previous = sentinel.previous;
        sentinel.previous.next = newnote;
        sentinel.previous = newnote;
        size++;
    }

    @Override
    public Item removeFirst(){
        if (isEmpty()) return null;
        Item item = sentinel.next.item;
        sentinel.next.next.previous = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return item;
    }
    @Override
    public Item removeLast(){
        if (isEmpty()) return null;
        Item item = sentinel.previous.item;
        sentinel.previous.previous.next = sentinel;
        sentinel.previous = sentinel.previous.previous;
        size--;
        return item;
    }
    @Override
    public Item get(int index){
        Note midnote = sentinel.next;
        if (size == 0 || index > size) return null;
        else for(int i = 0; i < index; i++){
            midnote = midnote.next;
        }
        return midnote.item;
    }
    @Override
    public void printDeque(){
        Note mid = sentinel;
        if (size == 0) System.out.println("null");
        for (int i = 0; i < size; i++){
            System.out.println(mid.next.item);
            mid = mid.next;
        }
    }
    //一个以递归实现的get()
    public Item getRecursive(int index){
        Note mid = sentinel;
        return getRecursive(mid.next,index);
    }
    public Item getRecursive(Note n,int index){
        if (index == 0) return n.item;
        n = n.next;
        index--;
        return getRecursive(n,index);
    }
    //实现Iterator
    private class LinkedListDequeIterator implements Iterator<Item> {
        private int pos;
        public LinkedListDequeIterator(){
            pos = 0;
        }
        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public Item next() {
            Item item = get(pos);
            pos += 1;
            return item;
        }
    }
    @Override
    public Iterator<Item> iterator(){
        return new LinkedListDequeIterator();
    }
}
