package demo14;

import com.sun.jmx.snmp.tasks.ThreadService;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号灯
 * Created by Administrator on 2016/7/5 0005.
 */
public class SemaphoreTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(3);//最多只能3个线程同时访问
        Random random = new Random();
        //模拟10个客户端访问
        for(int i=0;i<10;i++){
            threadService.execute(() -> {
                try {
                    semaphore.acquire();//获得许可
                    System.out.println("线程" + Thread.currentThread().getName() + "进入，当前已有" + (3 - semaphore.availablePermits()) + "个并发");
                    Thread.sleep(random.nextInt(1000));//模拟客户端使用
                    semaphore.release();
                    System.out.println("线程" + Thread.currentThread().getName() + "已离开，当前已有" +(3-semaphore.availablePermits()) + "个并发");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadService.shutdown();
        boolean done;
        do{
            done = !threadService.awaitTermination(1000, TimeUnit.SECONDS);
        }while (done);
    }
}
