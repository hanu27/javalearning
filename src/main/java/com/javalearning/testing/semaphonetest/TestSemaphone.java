package com.javalearning.testing.semaphonetest;

public class TestSemaphone {

    public static void main(String[] args) {
        SharedPrinter sharedPrinter = new SharedPrinter();
        Thread threadOdd = new Thread(new OddTask(sharedPrinter,10),"odd");
        Thread threadEven = new Thread(new EvenTask(sharedPrinter,10),"even");

        threadOdd.start();
        threadEven.start();
    }
}
