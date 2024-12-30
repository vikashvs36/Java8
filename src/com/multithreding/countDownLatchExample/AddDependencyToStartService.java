package executor.countDownLatchExample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/*
* How would you implement a scenario where a service starts only
* after multiple dependencies are initialized using CountDownLatch
* */
public class AddDependencyToStartService {
    public static void main(String[] args) throws InterruptedException {
        List<String> dependencyList = List.of("Kafka", "devtool", "cloud", "Web", "db");
        CountDownLatch latch = new CountDownLatch(dependencyList.size());
        Service service = new Service();

        dependencyList.forEach(dependency -> service.addDependency(new Dependency(dependency, latch)));

//        if (latch.getCount() == 0) {
            latch.countDown();
//        }
        latch.await();
        // Service will not be started until all dependencies have been initialized that we had mentioned in CountDownLatch
        System.out.println("All Dependencies Initialized...");
        service.startService();
    }
}

class Dependency extends Thread {
    private final CountDownLatch latch;
    public Dependency(String dependencyName, CountDownLatch latch) {
        super(dependencyName);
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Added Dependency : " + getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }
}

class Service {
    private final List<Dependency> dependencies = new ArrayList<>();

    public Service() {
        System.out.println("Service Initializing...");
    }

    public void addDependency(Dependency dependency) {
        dependencies.add(dependency);
        dependency.start();
    }

    public void startService() throws InterruptedException {
        System.out.println("All Dependency Added and Service Started...");
        System.out.println("Dependency Added List...");
        dependencies.stream().map(Dependency::getName).forEach(System.out::println);
        Thread.sleep(5000); // Simulating some service logic here
        System.out.println("Now Service Closed.");
    }
}
