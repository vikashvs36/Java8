package multithreding.completableFutureDemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class HandleComputableFutureException {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "Normal operation - 1");
        CompletableFuture<String> stringCompletableFuture1 = CompletableFuture.supplyAsync(() -> "Normal operation - 2");

        CompletableFuture<TsysResponse> stringCompletableFuture2 = stringCompletableFuture.thenCombine(stringCompletableFuture1, (a, b) -> new TsysResponse(a,b));
        System.out.println(stringCompletableFuture2.get());
        System.out.println("----------------------------------------------------------------");

        CompletableFuture<String> stringCompletableFuture4 = CompletableFuture.supplyAsync(() -> {
            throwException("First API call failed");
            return "Calling First API - Normal operation 1";
        }).exceptionallyAsync(ex -> {
            System.out.println();
            return "Exception throw - operation 2";
        });

        CompletableFuture<String> stringCompletableFuture5 = CompletableFuture.supplyAsync(() -> "Second API - Normal operation");
        CompletableFuture<String> stringCompletableFuture6 = stringCompletableFuture4.thenCombine(stringCompletableFuture, (a, b) -> a + "\n" + b);

        System.out.println("Individually handled Exception: " + stringCompletableFuture6.get());

        System.out.println("----------------------------------------------------------------\n");

        CompletableFuture<String> stringCompletableFuture3 = CompletableFuture.supplyAsync(() -> {
                    return "Normal operation 1";
                }).thenCombine(CompletableFuture.supplyAsync(() -> {
                    throwException("NullPointerException");
                    return "Exception throw - operation 1";
                }), (a, b) -> a + "\n" + b)
                .handleAsync((res, error) -> {
                    if (error != null) {
                        System.out.println("Error: " + error.getMessage());
                        return "Operation Failed";
                    }
                    return res;
                });
        System.out.println("Globally handled Exception: " + stringCompletableFuture3.get());
    }

    private static void throwException(String message) {
        throw new RuntimeException(message);
    }
}
