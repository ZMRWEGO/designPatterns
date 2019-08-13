package blockingQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 这是一个循环阻塞队列
 */
public class MyBlockingQueue {

    private final Object[] items;
    private final Condition notEmpty;
    private final Condition notFull;
    private ReentrantLock lock;
    private  int takeIndex=0;
    private  int putIndex=0;
    private int count = 0;

    public MyBlockingQueue(int capacity, boolean fair) {

        if (capacity <= 0)
            throw new IllegalArgumentException();
        //创建数组
        this.items = new Object[capacity];
        //创建锁和阻塞条件
        lock = new ReentrantLock(fair);
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    //添加元素的方法
    public void put(Object e) throws InterruptedException {
        final ReentrantLock lock = this.lock;
        //获得该锁 除非当前线程中断
        lock.lockInterruptibly();
        try {
            while (count == items.length)
                notFull.await();
            //如果队列不满就入队
            enqueue(e);
        } finally {
            lock.unlock();
        }
    }

    //入队的方法
    private void enqueue(Object x) {
        final Object[] items = this.items;
        items[putIndex] = x;
        if (++putIndex == items.length)
            putIndex = 0;
        count++;
        notEmpty.signal();
    }

    //移除元素的方法
    public Object take() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        //获得该锁 除非当前线程中断
        lock.lockInterruptibly();
        try {
            while (count == 0)
                notEmpty.await();
            return dequeue();
        } finally {
            lock.unlock();
        }
    }

    //出队的方法
    private Object dequeue() {
        final Object[] items = this.items;
        @SuppressWarnings("unchecked")
        Object x =  items[takeIndex];
        items[takeIndex] = null;
        if (++takeIndex == items.length)
            takeIndex = 0;
        count--;
        notFull.signal();
        return x;

    }
}
