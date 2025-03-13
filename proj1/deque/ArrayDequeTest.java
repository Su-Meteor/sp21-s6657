package deque;

import org.junit.Test;

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
        for (int i = 0; i < 100000;i++) ard.addLast(i);
        for (int i = 0; i < 100000;i++) assertEquals(i,(int)ard.removeFirst());
        for (int i = 0; i < 100000;i++) ard.addFirst(i);
        for (int i = 0; i < 100000;i++) assertEquals(i,(int)ard.removeLast());
    }
    @Test
    public void testGetMethod(){
        //
    }
}
