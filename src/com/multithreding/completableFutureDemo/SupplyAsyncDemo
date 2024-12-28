package multithreding.completableFutureDemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SupplyAsyncDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long l = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletableFuture<TsysContactResponse> tsysContactResponseCompletableFuture =
                CompletableFuture.supplyAsync(() -> SupplyAsyncDemo.tsysAccountApi(101), executorService)
                .thenApplyAsync(res -> SupplyAsyncDemo.tsysContactApi(res.account()), executorService);

//        System.out.println("Total time: " + (System.currentTimeMillis() - l)/1000 + "s");
        while(!tsysContactResponseCompletableFuture.isDone()) {
            TsysContactResponse tsysContactResponse = tsysContactResponseCompletableFuture.get();
            System.out.println("Response : "+ tsysContactResponse+ ", Total time: " + (System.currentTimeMillis() - l)/1000 + "s");
        }
        executorService.shutdown();
//        TsysContactResponse tsysContactResponse = tsysContactResponseCompletableFuture.get();
//        System.out.println("tsysContactResponse : "+ tsysContactResponse+ ", Total time: " + (System.currentTimeMillis() - l)/1000 + "s");
    }

    private static void getSyncDemo() throws ExecutionException, InterruptedException {
        CombineCompletableFuture es = new CombineCompletableFuture();

        CompletableFuture<TsysAccountResponse> tsysAccountResponseCompletableFuture =
                CompletableFuture.supplyAsync(() -> SupplyAsyncDemo.tsysAccountApi(1));


        CompletableFuture<TsysContactResponse> tsysContactResponseCompletableFuture =
                CompletableFuture.supplyAsync(() -> SupplyAsyncDemo.tsysContactApi("demo"));

        TsysAccountResponse tsysAccountResponse = tsysAccountResponseCompletableFuture.get();
        System.out.println("Response --------------------------------" + tsysAccountResponse);

        TsysContactResponse tsysContactResponse = tsysContactResponseCompletableFuture.get();
        System.out.println("Response --------------------------------" + tsysContactResponse);
    }

    private static TsysAccountResponse tsysAccountApi(int userId) {
        System.out.println("TsysAccountResponse started : " +Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("TsysAccountResponse completed successfully. " +Thread.currentThread().getName());
        return new TsysAccountResponse("AC-"+userId);
    }

    private static TsysContactResponse tsysContactApi(String account){
        System.out.println("TsysContactResponse started : " +Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("TsysContactResponse completed successfully. " +Thread.currentThread().getName());
        return new TsysContactResponse("email"+account+"@gmail.com");
    }
}

record TsysAccountResponse(String account) { }
record TsysContactResponse(String emailId) { }
