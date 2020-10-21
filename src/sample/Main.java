package sample;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
public class Main {
    //task that takes time to complete
    public static int t1() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Task 1 Completed");
        return 100;
    }
    //not dependent on doSomeHeavyTask()
    //doesn't takes too much time
    public static int t2() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("Task 2 Completed");
        return 10;
    }
    //dependent on t1
    //not dependent on t2
    public static void t3(int res) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Task 3 completed. Output : " + res);
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //t1
        //t2
        Main.calculateTime();
    }
    public static void calculateTime() throws InterruptedException, ExecutionException {
        long st = System.currentTimeMillis();
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        Future<Integer> future = executor.submit(() -> {
//            try {
//                return Main.t1();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return -1;
//        });
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return t1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return -1;
        });
        t2();
        completableFuture.thenAcceptAsync(integer -> {
            try {
                t3(integer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        System.out.println("All tasks completed");
//        t3(future.get());
        System.out.println("All tasks completed.");
        long et = System.currentTimeMillis();
        System.out.println(et - st);
        Thread.sleep(7000);
    }
    public static void runTasks() throws InterruptedException {
    }
}