package com.javalearning.testing.multithreading;

public class MultiThreading {
    int counter = 0;
    int max = 10;

    public static void main(String[] args) throws InterruptedException {
        MultiThreading multiThreading = new MultiThreading();

        Thread threadEven = new Thread(() -> {
            multiThreading.incrementCounter();

        }, "thread 1 ");
        Thread threadOdd = new Thread(() -> {
            multiThreading.incrementCounter();

        }, "thread 2 ");

        threadOdd.start();
        threadEven.start();
    }

    private synchronized void runEven() {
        do {
            if (counter % 2 == 0) {
                System.out.println
                        (Thread.currentThread().getName() + " counter " + counter);
                counter++;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } while (counter <= max);

    }

    public synchronized void runOdd() {
        do {
            if (counter % 2 == 1) {
                System.out.println
                        (Thread.currentThread().getName() + " counter " + counter);
                counter++;
                notifyAll();
            } else {
                try {

                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } while (counter <= max);
    }

    public synchronized void incrementCounter() {
        do {
            try {
                counter++;
                System.out.println(Thread.currentThread().getName() + "==" + counter);
                notify();
                wait();
            } catch (InterruptedException interruptedException) {
                System.out.println(Thread.currentThread().getName() + "=="
                        + interruptedException.getMessage());
            }

        } while (counter < 10);
        notify();
    }
}
