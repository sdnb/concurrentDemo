package demo12;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 读锁与读锁不互斥，读锁和写锁，写锁和写锁互斥
 * Created by Administrator on 2016/7/4 0004.
 */
public class ReadWriteLockTest {



    private ReadWriteLock rwl = new ReentrantReadWriteLock();


    public static void main(String[] args) {
        Data data = new Data();
        ReadWriteLockTest rwlt = new ReadWriteLockTest();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
       while (true){
           executorService.execute(()->data.getData());
           executorService.execute(()->data.setData());
       }


    }

}

class Data{
    private ReadWriteLock rwl = new ReentrantReadWriteLock();
    private Object data = null;

    public void getData(){
        rwl.readLock().lock();

        try {System.out.println(Thread.currentThread().getName() + " ready to get data...... ");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+" get data "+data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rwl.readLock().unlock();
        }

    }

    public void setData(){
        rwl.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " ready to set data...... ");
            Thread.sleep(1000);
            Random random = new Random();
            data = random.nextInt();
            System.out.println(Thread.currentThread().getName() + " set data " + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rwl.writeLock().unlock();
        }


    }
}
