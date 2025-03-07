package com.javalearning.testing;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;

@Slf4j
public class Vtexample {
    static Logger log = LoggerFactory.getLogger(Vtexample.class);

    @SneakyThrows
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        log.info("start");
        for (int i = 0; i < 2; i++) {
            log.info("start "+ i);
            CompletableFuture<Void> future1 = CompletableFuture.supplyAsync(getSupplier(i),executorService);
            CompletableFuture<Void> future2 = CompletableFuture.supplyAsync(getSupplier(i),executorService);
            CompletableFuture<Void> future3 = CompletableFuture.supplyAsync(getSupplier(i),executorService);
            CompletableFuture<Void> future4 = CompletableFuture.supplyAsync(getSupplier(i),executorService);
            CompletableFuture<Void> future5 = CompletableFuture.supplyAsync(getSupplier(i),executorService);
            CompletableFuture<Void> future6 = CompletableFuture.supplyAsync(getSupplier(i),executorService);
            CompletableFuture<Void> future7 = CompletableFuture.supplyAsync(getSupplier(i),executorService);
//            future1.get();
//            future2.get();
//            future3.get();
//            future4.get();
//            future5.get();
//            future6.get();
//            future7.get();

            CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(future1, future2, future3, future4, future5, future6, future7);
            int finalI = i;
            voidCompletableFuture.whenCompleteAsync((unused, throwable) -> System.out.println("its complete" + finalI));
            log.info("end "+ i);
        }
        log.info("end");

        executorService.shutdown();
    }

    private static Runnable getRunnable(int i) {
        Runnable task = () -> {
            extracted(i);
        };
        return task;
    }
    
    private static Supplier getSupplier(int i ){
        return () -> {
            extracted(i);
            return null;
        };
    }

    private static void extracted(int i) {
        log.info("Inside extracted "+ i);
        long startTime = System.currentTimeMillis();
        Random random = new Random();

        Double total = 0d;
        while (System.currentTimeMillis() - startTime < 3000) {
            double sum = DoubleStream.generate(() -> random.nextDouble())
                    .limit(random.nextInt(100))
                    .map(n -> Math.cos(n))
                    .count();
            total+=sum;
        }
        log.info("Done i"+i);
    }


}
