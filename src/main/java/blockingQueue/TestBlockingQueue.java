package blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TestBlockingQueue {
    //初始化一个容量为3的阻塞队列
    final static BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);

    public static void main(String[] args) {
        for (int i = 0; i <2 ; i++) {
            Producer producer = new Producer();
            producer.start();
        }
        Consumer consumer = new Consumer();
        consumer.start();
    }
}
//定义一个生产者
class Producer extends Thread{
    @Override
    public void run() {
        while (true) {
            try {
               // Thread.sleep((long) Math.random() * 1000);
                System.out.println(Thread.currentThread().getName()+"号正准备生产数据"+(TestBlockingQueue.blockingQueue.size()==3?"对列已满正在等待":"..."));
                TestBlockingQueue.blockingQueue.put("byte");
                System.out.println(Thread.currentThread().getName()
                        + "存入数据，" + "队列目前有" + TestBlockingQueue.blockingQueue.size()
                        + "个数据");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
//定义一个消费者
class Consumer extends Thread{
    @Override
    public void run() {
        while (true) {
            try {
               // Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+"号正在消费数据"+(TestBlockingQueue.blockingQueue.size()==0?"队列空，正在等待":"..."));
                TestBlockingQueue.blockingQueue.take();
                System.out.println(Thread.currentThread().getName()
                        + "取出数据，" + "队列目前有" + TestBlockingQueue.blockingQueue.size()
                        + "个数据");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
