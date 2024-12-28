package multithreding.completableFutureDemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CombineCompletableFuture {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long l = System.currentTimeMillis();
        CombineCompletableFuture cf = new CombineCompletableFuture();
//        Combine 2 dependent Future
        CompletableFuture<TsysContactResponse> tsysContactResponseCompletableFuture = cf.tsysAccountApi(100)
                .thenCompose(obj -> cf.tsysContactApi(obj.account()));
        TsysContactResponse tsysContactResponse = tsysContactResponseCompletableFuture.get();
        System.out.println("Response : "+ tsysContactResponse + ", Total time: " + (System.currentTimeMillis() - l)/1000 + "s");
        System.out.println("----------------------------------------------------------------");

//        Combine 2 Independent Future
        long l2 = System.currentTimeMillis();
        CompletableFuture<TsysResponse> tsysResponseCompletableFuture = cf.tsysAccountApi(101)
                .thenCombine(cf.tsysContactApi("1012"),
                        (accountApi, contactApi) ->
                                new TsysResponse(accountApi.account(), contactApi.emailId()));
        TsysResponse tsysResponse = tsysResponseCompletableFuture.get();
        System.out.println("Response : "+ tsysResponse + ", Total time: " + (System.currentTimeMillis() - l2)/1000 + "s");
        System.out.println("----------------------------------------------------------------");

//        Combine multiple Future
        long l3 = System.currentTimeMillis();
        CompletableFuture<TsysAccountResponse> tsysAccountResponseCompletableFuture = cf.tsysAccountApi(103);
        CompletableFuture<TsysContactResponse> tsysContactResponseCompletableFuture1 = cf.tsysContactApi("AC-103");

        CompletableFuture<Void> allDone = CompletableFuture.allOf(tsysAccountResponseCompletableFuture, tsysContactResponseCompletableFuture1);
        allDone.thenRunAsync(() -> {
            TsysAccountResponse account = tsysAccountResponseCompletableFuture.join();
            TsysContactResponse contact = tsysContactResponseCompletableFuture1.join();
            System.out.println(account);
            System.out.println(contact);
        }).join();
        System.out.println("Total time: " + (System.currentTimeMillis() - l3)/1000 + "s");
        System.out.println("----------------------------------------------------------------");

//        Combine multiple Future (Without waiting)
        long l4 = System.currentTimeMillis();
        CompletableFuture<TsysAccountResponse> tsysAccountResponseCompletableFuture1 = cf.tsysAccountApi(104);
        CompletableFuture<TsysContactResponse> tsysContactResponseCompletableFuture2 = cf.tsysContactApi("AC-104");

        CompletableFuture<Object> objectCompletableFuture =
                CompletableFuture.anyOf(tsysAccountResponseCompletableFuture1, tsysContactResponseCompletableFuture2);
        objectCompletableFuture.thenAccept(obj -> System.out.println(obj))
                .join();

        System.out.println("Total time: " + (System.currentTimeMillis() - l4)/1000 + "s");
        System.out.println("----------------------------------------------------------------");

    }

    private CompletableFuture<TsysAccountResponse> tsysAccountApi(int userId) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("TsysAccountResponse started : " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("TsysAccountResponse completed successfully. " + Thread.currentThread().getName());
            return new TsysAccountResponse("AC-" + userId);
        });
    }

    private CompletableFuture<TsysContactResponse> tsysContactApi(String account) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("TsysContactResponse started : " + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("TsysContactResponse completed successfully. " + Thread.currentThread().getName());
            return new TsysContactResponse("email" + account + "@gmail.com");
        });
    }
}

record TsysResponse(String account, String email) { }