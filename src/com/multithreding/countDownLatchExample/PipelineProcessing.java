package executor.countDownLatchExample;

import java.util.concurrent.CountDownLatch;

/*
* Let's implement a three-stage pipeline:

* Stage 1: Tasks prepare data.
* Stage 2: Tasks process data.
* Stage 3: Tasks finalize data.
* */

public class PipelineProcessing {
    public static void main(String[] args) throws InterruptedException {
        int numberOfTasks = 3;

        // Latches for stage synchronization
        CountDownLatch productionBuild1 = new CountDownLatch(numberOfTasks);
        CountDownLatch productionBuild2 = new CountDownLatch(numberOfTasks);

        // Stage 1: Data preparation
        Runnable developmentTask = () -> {
            System.out.println(Thread.currentThread().getName() + " completed Stage 1");
            productionBuild1.countDown(); // Indicate task completion
        };

        // Stage 2: Data processing
        Runnable qaTask = () -> {
            try {
                productionBuild1.await(); // Wait for Stage 1 to complete
                System.out.println(Thread.currentThread().getName() + " completed Stage 2");
                productionBuild2.countDown(); // Indicate task completion
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Stage 3: Data finalization
        Runnable productionTask = () -> {
            try {
                productionBuild2.await(); // Wait for Stage 2 to complete
                System.out.println(Thread.currentThread().getName() + " completed Stage 3");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Start tasks
        for (int i = 0; i < numberOfTasks; i++) {
            new Thread(developmentTask, "Dev Deployment Task-" + i).start();
            new Thread(qaTask, "QA Deployment Task-" + i).start();
            new Thread(productionTask, "Production Deployment Task-" + i).start();
        }
    }
}
