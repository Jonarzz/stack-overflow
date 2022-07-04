package io.github.jonarzz;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class InitialWithLogging {

    private static final Semaphore semA = new Semaphore(2);
    private static final Semaphore semB = new Semaphore(0);

    public static void main(String[] args) {
        while (true) {
            new P1A().start();
            new P2B().start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class P1A extends Thread {

        private static final AtomicInteger THREAD_COUNTER = new AtomicInteger();

        P1A() {
            // could easily be just a field in current class, but let's use thread-related mechanisms from JDK
            super("Thread-A" + THREAD_COUNTER.incrementAndGet());
        }

        @Override
        public void run() {
            var threadName = Thread.currentThread().getName();
            try {
                semA.acquire();
                System.out.println("A" + " (" + threadName + ")");
                if (semA.availablePermits() == 0) {
                    semB.release(2);
                    System.out.println(threadName + " released B");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class P2B extends Thread {

        private static final AtomicInteger THREAD_COUNTER = new AtomicInteger();

        P2B() {
            // could easily be just a field in current class, but let's use thread-related mechanisms from JDK
            super("Thread-B" + THREAD_COUNTER.incrementAndGet());
        }

        @Override
        public void run() {
            var threadName = Thread.currentThread().getName();
            try {
                semB.acquire();
                System.out.println("B" + " (" + threadName + ")");
                if (semB.availablePermits() == 0) {
                    semA.release(2);
                    System.out.println(threadName + " released A");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
