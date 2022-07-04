package io.github.jonarzz;

import java.util.concurrent.Semaphore;

public class InitialFixedWithComments {

    private static final Semaphore semA = new Semaphore(2);
    private static final Semaphore semB = new Semaphore(0);

    public static void main(String[] args) {
        while (true) {
            // each loop is creating a new thread
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

        @Override
        public void run() {
            try {
                // multiple threads can be unlocked after acquisition in parallel
                semA.acquire();
                System.out.print("A");
                // multiple threads can get the same result from the if below
                if (semA.availablePermits() == 0) {
                    synchronized (semB) {
                        if (semB.availablePermits() == 0) {
                            // this could be done using a compare-and-swap mechanism
                            // a locking abstraction could be extracted to a method that would accept 2 parameters
                            // the expected (assumed) current state and the incrementation value
                            // if current (actual) state was different from expected, value would not be modified
                            semB.release(2);
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class P2B extends Thread {

        @Override
        public void run() {
            try {
                // multiple threads can be unlocked after acquisition in parallel
                semB.acquire();
                System.out.print("B");
                // multiple threads can get the same result from the if below
                if (semB.availablePermits() == 0) {
                    // to avoid threads releasing multiple times in case of reaching this point in parallel
                    // an additional synchronization has to be added to avoid a race
                    synchronized (semA) {
                        if (semA.availablePermits() == 0) {
                            // this could be done using a compare-and-swap mechanism
                            // a locking abstraction could be extracted to a method that would accept 2 parameters
                            // the expected (assumed) current state and the incrementation value
                            // if current (actual) state was different from expected, value would not be modified
                            semA.release(2);
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
