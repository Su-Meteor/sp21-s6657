package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        SLList<Integer> test = new SLList<>();
        AList<Integer> ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        int m = 10000;

        for (int N = 1000; N <= 128000; N *= 2){
            ns.addLast(N);
            opCounts.addLast(m);
        }
        for (int i = 0; i < ns.size(); i++){
            for (int j = 0; j < ns.get(i); j++) test.addLast(1);
            Stopwatch sw = new Stopwatch();
            for (int k = 0; k < opCounts.get(i); k++) test.getLast();
            times.addLast(sw.elapsedTime());
        }

        printTimingTable(ns, times, opCounts);
    }

}
