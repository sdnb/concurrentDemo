package demo13;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现一个缓冲队列，多个线程往队列中存数据，也有多个线程同时从队列中取数据，
 * refrence of class Condition's javadoc comment
 *
 *  class BoundedBuffer {
 *   final Lock lock = new ReentrantLock();
 *   final Condition notFull  = lock.newCondition();
 *   final Condition notEmpty = lock.newCondition();
 *
 *   final Object[] items = new Object[100];
 *   int putptr, takeptr, count;
 *
 *   public void put(Object x) throws InterruptedException {
 *   lock.lock();
 *     try {
 *       while (count == items.length)
 *         notFull.await();
 *       items[putptr] = x;
 *       if (++putptr == items.length) putptr = 0;
 *       ++count;
 *      notEmpty.signal();
 *   } finally {
 *       lock.unlock();
 *     }
 *   }
 *
 *   public Object take() throws InterruptedException {
 *    lock.lock();
 *     try {
 *       while (count == 0)
 *         notEmpty.await();
 *       Object x = items[takeptr];
 *       if (++takeptr == items.length) takeptr = 0;
 *       --count;
 *       notFull.signal();
 *       return x;
 *    } finally {
 *       lock.unlock();
 *     }
 *   }
 *
 * Created by Administrator on 2016/7/5 0005.
 */
public class BufferQueue {
    final Object[] items = new Object[20];
    int putIndex ,getIndex,count;

    Lock lock = new ReentrantLock();
    Condition notFull = lock.newCondition();
    Condition notEmpty= lock.newCondition();
    public void put(Object obj) throws InterruptedException {
        lock.lock();
        try {
            while (count==items.length)
                notFull.await();
            items[putIndex]=obj;
            if(++putIndex==items.length) putIndex=0;
            count++;
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    public Object get() throws InterruptedException {
        lock.lock();
        try {
            Object x = null;
            while (count==0)
                notEmpty.await();
            x = items[getIndex];
            if(++getIndex==items.length) getIndex=0;
            count--;
            notFull.signal();
            return x;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BufferQueue bq = new BufferQueue();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        while (true){
            Random random = new Random();
            Thread.sleep(random.nextInt(1000));
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        bq.put(random.nextInt(10000));
                        System.out.println(Arrays.toString(bq.items));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                       int x = (Integer) bq.get();
                        System.out.println("原队列数组：");
                        System.out.println(Arrays.toString(bq.items));
                        System.out.println("取出数据后的队列数组：");
                        System.out.println(Arrays.toString(bq.items));
                        System.out.println("取出数字："+ x);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


    }
}
