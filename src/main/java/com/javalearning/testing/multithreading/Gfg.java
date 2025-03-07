package com.javalearning.testing.multithreading;

class Gfg extends Thread {

    // using volatile
    volatile boolean working = true;

    // if non-volatile it will
    // sleep in main and
    // runtime error will coocur
    public void run()
    {
        System.out.println("Inside run");
        long count = 0;
        while (working) {
//            System.out.println("inside while");
            count++;
        }

        System.out.println("Thread terminated."
                + count);
    }



    // Driver code
    public static void main(String[] args)
            throws InterruptedException
    {
        Gfg test = new Gfg();
        test.start();
        Thread.sleep(100);
        System.out.println("After Sleeping in Main");
        test.working = false;
        test.join();
        System.out.println("Working set to "
                + test.working);
    }
}

