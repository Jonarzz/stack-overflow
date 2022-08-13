package io.github.jonarzz;

public class StopWatch {

    private long start = time();

    public long diff() {
        return time() - start;
    }

    private static long time() {
        return System.nanoTime();
    }
}
