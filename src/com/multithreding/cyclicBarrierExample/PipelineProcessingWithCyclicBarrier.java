package executor.cyclicBarrierExample;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class PipelineProcessingWithCyclicBarrier {
    public static void main(String[] args) {
        int numberOfTasks = 3;

        // Barriers for stage synchronization
        CyclicBarrier stage1Barrier = new CyclicBarrier(numberOfTasks, 
            () -> System.out.println("All tasks completed Stage 1. Proceeding to Stage 2."));
        CyclicBarrier stage2Barrier = new CyclicBarrier(numberOfTasks,
            () -> System.out.println("All tasks completed Stage 2. Proceeding to Stage 3."));

        // Stage 1: Data preparation
        Runnable stage1Task = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " completed Stage 1");
                stage1Barrier.await(); // Wait for all tasks in Stage 1
            } catch (InterruptedException | BrokenBarrierException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Stage 2: Data processing
        Runnable stage2Task = () -> {
            try {
                stage1Barrier.await(); // Wait for Stage 1 to complete
                System.out.println(Thread.currentThread().getName() + " completed Stage 2");
                stage2Barrier.await(); // Wait for all tasks in Stage 2
            } catch (InterruptedException | BrokenBarrierException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Stage 3: Data finalization
        Runnable stage3Task = () -> {
            try {
                stage2Barrier.await(); // Wait for Stage 2 to complete
                System.out.println(Thread.currentThread().getName() + " completed Stage 3");
            } catch (InterruptedException | BrokenBarrierException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Start tasks
        for (int i = 0; i < numberOfTasks; i++) {
            new Thread(stage1Task, "Task-" + i).start();
            new Thread(stage2Task, "Task-" + i).start();
            new Thread(stage3Task, "Task-" + i).start();
        }
    }
}
