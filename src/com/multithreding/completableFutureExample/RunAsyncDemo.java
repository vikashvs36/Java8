package multithreding.completableFutureDemo;

import java.util.concurrent.*;

public class RunAsyncDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        long l = System.currentTimeMillis();
        RunAsyncDemo es = new RunAsyncDemo();

        /*for (int i=0; i< 5; i++) {
            executorService.execute(() -> {
                try {
                    es.tsysApi1();
                    es.tsysApi2();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            *//*Thread t = new Thread(() -> {
                try {
                    es.tsysApi1();
                    es.tsysApi2();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            t.start();
            t.join();*//*
        }*/

        /*
        Future<TsysAccountResponse> submit = executorService.submit(() -> es.tsysAccountApi(1));
        System.out.println("Result : "+ submit.get());

        Future<TsysContactResponse> res2 = executorService.submit(() -> es.tsysContactApi(submit.get().account()));
        System.out.println("Result : "+res2.get());

        while (!executorService.isTerminated()) {
            executorService.shutdown();
        }*/

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            try {
                es.tsysAccountApi(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                es.tsysContactApi("1");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, executorService);
        voidCompletableFuture.get();
        System.out.println("Total time: " + (System.currentTimeMillis() - l)/1000 + "s");
        executorService.shutdown();
    }

    public void tsysAccountApi(int userId) throws InterruptedException {
        System.out.println("tsysApi1 started : " +Thread.currentThread().getName());
        Thread.sleep(2000);
        System.out.println("tsysApi1 completed successfully. " +Thread.currentThread().getName());
    }

    public void tsysContactApi(String account) throws InterruptedException {
        System.out.println("tsysApi1 started : " +Thread.currentThread().getName());
        Thread.sleep(2000);
        System.out.println("tsysApi1 completed successfully. " +Thread.currentThread().getName());
    }
}