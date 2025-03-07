package com.javalearning.testing.semaphonetest;

public class OddTask implements Runnable {
    private SharedPrinter sharedPrinter;
    private int max;

    public OddTask(SharedPrinter sharedPrinter, int max) {
        this.sharedPrinter = sharedPrinter;
        this.max = max;
    }

    @Override
    public void run() {
        for (int i = 1; i < max; i = i+2) {
            sharedPrinter.printOdd(i);
        }
    }
}
