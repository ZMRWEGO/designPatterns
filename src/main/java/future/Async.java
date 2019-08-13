package future;

import java.util.concurrent.*;

public class Async {

    static Task task = new Task();
    static ExecutorService es = Executors.newCachedThreadPool();
    static FutureTask<String> fs = new FutureTask<>(task);


    public static void main(String[] args) {
        es.submit(fs);
        es.shutdown();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            System.out.println("task运行结果"+fs.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }




}

class Task implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "I'm Ready";
    }
}
