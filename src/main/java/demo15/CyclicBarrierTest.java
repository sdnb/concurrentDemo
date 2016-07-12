package demo15;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 栅栏
 * 阻塞n个线程，直到n个线程都到达了，才释放这n线程下一步操作，这种技术称为栅栏技术
 * Created by Administrator on 2016/7/6 0006.
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        Random random = new Random();
        ExecutorService es = Executors.newFixedThreadPool(10);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        for(int i=0;i<6;i++){
            es.execute(()->{
                try {
                    Thread.sleep(random.nextInt(1000));

                    System.out.println("线程：" + Thread.currentThread().getName() + "即将到达集合地点1，当前已有" + (cyclicBarrier.getNumberWaiting()+1) + "个线程在等待");
                    cyclicBarrier.await();
                    Thread.sleep(random.nextInt(1000));

                    System.out.println("线程：" + Thread.currentThread().getName() + "即将到达集合地点2，当前已有" + (cyclicBarrier.getNumberWaiting()+1) + "个线程在等待");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        es.shutdown();
    }
}
