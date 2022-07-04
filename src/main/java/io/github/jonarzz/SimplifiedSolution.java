package io.github.jonarzz;

import java.util.concurrent.Semaphore;

public class SimplifiedSolution {

    public static void main(String[] args) {
        var aSemaphore = new Semaphore(2);
        var bSemaphore = new Semaphore(0);
        while (true) {
            new PrintingThread("A", aSemaphore, bSemaphore).start();
            new PrintingThread("B", bSemaphore, aSemaphore).start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class PrintingThread extends Thread {

        private final String valueToPrint;
        private final Semaphore acquiredSemaphore;
        private final Semaphore releasedSemaphore;

        PrintingThread(String valueToPrint, Semaphore acquiredSemaphore, Semaphore releasedSemaphore) {
            this.valueToPrint = valueToPrint;
            this.acquiredSemaphore = acquiredSemaphore;
            this.releasedSemaphore = releasedSemaphore;
        }

        @Override
        public void run() {
            try {
                acquiredSemaphore.acquire();
                System.out.print(valueToPrint);
                if (acquiredSemaphore.availablePermits() == 0) {
                    synchronized (releasedSemaphore) {
                        if (releasedSemaphore.availablePermits() == 0) {
                            releasedSemaphore.release(2);
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
