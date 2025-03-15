package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void testAdd_and_RemoveMethod(){
        ArrayDeque<Integer> ard = new ArrayDeque<>();
        ard.addLast(1);
        ard.addLast(2);
        ard.addFirst(3);
        assertEquals(3,(int)ard.removeFirst());
        assertEquals(1,(int)ard.removeFirst());
        assertEquals(2,(int)ard.removeLast());
        assertNull(ard.removeFirst());
        assertNull(ard.removeLast());
    }
    @Test
    public void testAdd_and_RemoveInBigNumber(){
        ArrayDeque<Integer> ard = new ArrayDeque<>();
        for (int i = 0; i < 100000;i++) {
            ard.addLast(i);
        }
        for (int i = 0; i < 100000;i++) {
            assertEquals(i,(int)ard.removeFirst());
        }
        for (int i = 0; i < 100000;i++) {
            ard.addFirst(i);
        }
        for (int i = 0; i < 100000;i++) {
            assertEquals(i,(int)ard.removeLast());
        }
    }
    @Test
    public void testGetMethod(){
        ArrayDeque<Integer> ard1 = new ArrayDeque<>();
        ArrayDeque<String> ard2 = new ArrayDeque<>();
        ArrayDeque<Double> ard3 = new ArrayDeque<>();
        ArrayDeque<Boolean> ard4 = new ArrayDeque<>();
        for(int i = 0;i < 5;i++){
            ard1.addLast(i);
        }
        for(int i = 0;i < 5;i++){
            assertEquals(i,(int)ard1.get(i));
        }
        ard2.addLast("A");
        ard2.addLast("B");
        assertEquals("A",ard2.get(0));
        assertEquals("B",ard2.get(1));
        ard3.addLast(3.5);
        ard3.addLast(7.0);
    }
    @Test
    public void testPrintDeque(){
        ArrayDeque<Integer> ard = new ArrayDeque<>();
        for(int i = 0; i < 100;i++){
            ard.addLast(i);
        }
        ard.printDeque();
    }
    @Test
    public void testIterator(){
        ArrayDeque<Integer> ard = new ArrayDeque<>();
        for (int i = 0; i < 10;i++){
            ard.addLast(i);
        }
        Iterator<Integer> seer = ard.iterator();
        for (int i = 0; i < 9; i++) {
            assertEquals(i, (int) seer.next());
        }
        assertFalse(seer.hasNext());
        assertEquals(9,(int)seer.next());

        ArrayDeque<Integer> ard1 = new ArrayDeque<>();
        for (int i = 0; i < 10;i++){
            ard1.addLast(i);
        }
        Iterator<Integer> seer1 = ard1.iterator();
        for (int i = 0; i < 100000; i++) {
            seer.next();
        }
        System.out.println(seer.hasNext());
    }
    @Test
    public void testEquals(){
        ArrayDeque<Integer> ard1 = new ArrayDeque<>();
        ArrayDeque<Integer> ard2 = new ArrayDeque<>();
        for(int i = 0;i < 10;i++){
            ard1.addLast(i);
            ard2.addLast(i);
        }
        assertTrue(ard1.equals(ard2));
        assertTrue(ard2.equals(ard1));
        ard1.addLast(10);
        assertFalse(ard1.equals(ard2));
        ard1.removeLast();
        ard1.removeLast();
        ard1.addLast(10);
        assertFalse(ard1.equals(ard2));
    }
}
