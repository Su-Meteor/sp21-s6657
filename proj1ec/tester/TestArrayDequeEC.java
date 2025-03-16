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
                        assertEquals("RemoveFirst() Test Failed!\nWe expected it is" + ads.removeFirst()
                                + ",But Actually it is" + sad.removeFirst() + ".",
                                ads.removeFirst(), sad.removeFirst());
                    }
                    break;
                case 3:
                    if (!ads.isEmpty()) {
                        assertEquals("RemoveLast() Test Failed!\nWe expected it is" + ads.removeLast()
                                        + ",But Actually it is" + sad.removeLast() + ".",
                                ads.removeLast(), sad.removeLast());
                    }
                    break;
                default:
                    break;
            }
        }
    }
    @Test
    public void getMethodTest(){
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        for (int i = 0; i < 1000; i++){
            int opNum = StdRandom.uniform(0, 2);
            if (opNum == 0){
                sad.addFirst(i);
                ads.addFirst(i);
            } else if (opNum == 1) {
                sad.addLast(i);
                ads.addLast(i);
            }
        }
        for (int i = 0; i < 1000; i++) {
            assertEquals("Get() Test Failed!\nWe expected it is" + ads.get(i)
                            + ",But Actually it is" + sad.get(i) + ".",
                    ads.get(i), sad.get(i));
        }
    }
}
