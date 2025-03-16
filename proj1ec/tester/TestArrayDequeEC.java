package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {
    @Test
    public void stdRandomTest() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        int opNum = 100000;
        for (int i = 0; i < opNum; i++) {
            int ctrlNum = StdRandom.uniform(0, 4);
            switch (ctrlNum) {
                case 0:
                    sad.addFirst(i);
                    ads.addFirst(i);
                    break;
                case 1:
                    sad.addLast(i);
                    ads.addLast(i);
                    break;
                case 2:
                    if (!ads.isEmpty()) {
                        assertEquals("message", sad.removeFirst(), ads.removeFirst());
                    }
                    break;
                case 3:
                    if (!ads.isEmpty()) {
                        assertEquals("message", sad.removeLast(), ads.removeLast());
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
