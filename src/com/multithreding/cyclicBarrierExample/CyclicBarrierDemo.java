package executor.cyclicBarrierExample;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("All threads reached the barrier. Proceeding to next phase."));

        Runnable task = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " reached the barrier.");
                barrier.await();
                System.out.println(Thread.currentThread().getName() + " proceeding.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        new Thread(task).start();
        Thread.sleep(2000);
        new Thread(task).start();
        Thread.sleep(2000);
        new Thread(task).start();
    }
}
