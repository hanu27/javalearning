package com.javalearning.testing.semaphonetest;

public class EvenTask implements Runnable {
    private int max;
    private SharedPrinter sharedPrinter;


    public EvenTask(SharedPrinter sharedPrinter, int max) {
        this.sharedPrinter = sharedPrinter;
        this.max = max;
    }
    @Override
    public void run() {
        for (int i = 0; i < max; i = i+2) {
            sharedPrinter.printEven(i);
        }
    }
}
