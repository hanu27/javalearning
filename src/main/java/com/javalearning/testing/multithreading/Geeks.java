package com.javalearning.testing.multithreading;
// Java program to demonstrate the
// volatile modifier with non-atomic increment
class Counter {
    private volatile int count;   // Volatile variable

    public void increment() {
        count++;   // This operation is not atomic
    }

    public int getCount() {
        return count;
    }
}

public class Geeks {
    public static void main(String[] args) {
        Counter c = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                c.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                c.increment();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Count: " + c.getCount());
    }
}
