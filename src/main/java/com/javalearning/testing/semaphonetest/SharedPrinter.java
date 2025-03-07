package com.javalearning.testing.semaphonetest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Semaphore;

public class SharedPrinter {
    private final Semaphore semEven = new Semaphore(1);
    private final Semaphore semOdd = new Semaphore(0);

    public void printOdd(int i) {
        try {
            semOdd.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " : " + i);
        delay();
        semEven.release();
    }

    private static void delay() {
//        LocalDateTime endTime = LocalDateTime.now().plus(5, ChronoUnit.SECONDS);
//
//        while (endTime.until(LocalDateTime.now(),ChronoUnit.SECONDS) < 0 ){
//
//        }
    }

    public void printEven(int i) {
        try {
            semEven.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + " : " + i);
        delay();
        semOdd.release();
    }
}


