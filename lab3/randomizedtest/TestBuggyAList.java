package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> NA = new AListNoResizing<>();
        BuggyAList<Integer> BA = new BuggyAList<>();
        NA.addLast(4);
        BA.addLast(4);
        NA.addLast(5);
        BA.addLast(5);
        NA.addLast(6);
        BA.addLast(6);
        assertEquals(NA.removeLast(),BA.removeLast());
        assertEquals(NA.removeLast(),BA.removeLast());
        assertEquals(NA.removeLast(),BA.removeLast());
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();

        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 2);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                System.out.println("size: " + size);
            }
        }
    }

    @Test
    public void moreRandomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> BA = new BuggyAList<>();

        int N = 5000;

        for (int i = 0; i < N; i++){
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber ==  0 && L.size() >= 0){
                int randVal = StdRandom.uniform(0,100);
                L.addLast(randVal);
                BA.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            }
            if (operationNumber ==  1 && L.size() > 0){
                int Val1 = L.removeLast();
                int Val2 = BA.removeLast();
                assertEquals(Val1, Val2);
                System.out.println("removeLast(" + Val1 + ")");
            }
            if(operationNumber == 2){
                int size1 = L.size();
                int size2 = BA.size();
                assertEquals(size1, size2);
                System.out.println("size: " + size1);
            }
        }
    }
}
