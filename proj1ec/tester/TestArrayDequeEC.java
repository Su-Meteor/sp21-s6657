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
            if (ctrlNum == 0) {
                sad.addFirst(i);
                ads.addFirst(i);
            } else if (ctrlNum == 1) {
                sad.addLast(i);
                ads.addLast(i);
            } else if (ctrlNum == 2) {
                if (!ads.isEmpty() && !sad.isEmpty()) {
                    int expect = ads.removeFirst();
                    int actual = sad.removeFirst();
                    assertEquals("removeFirst() Test Failed!\nWe expected it is>" + expect
                                    + "<,But Actually it is>" +actual + "<.",
                            expect, actual);
                }
            } else if (ctrlNum == 3) {
                if (!ads.isEmpty() && !sad.isEmpty()) {
                    int expect = ads.removeLast();
                    int actual = sad.removeLast();
                    assertEquals("removeFirst() Test Failed!\nWe expected it is" + expect
                                    + ",But Actually it is" + actual + ".",
                            expect, actual);
                }
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
//    public static void main(String[] args) {
//        jh61b.junit.TestRunner.runTests(TestArrayDequeEC.class);
//    }
}
