package juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class MySemaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        long t = 1000;
                        System.out.println("休眠"+t +"ms");
                        Thread.sleep(t);
                        semaphore.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
         es.execute(run);
        }
    }
}
