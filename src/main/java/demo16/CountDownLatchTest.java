package demo16;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 闭锁
 * 一种同步工具类，可以一组延迟线程的进度，直到该闭锁到达终止状态。
 * 相当于一扇门，在到达终止状态前，这个门一直是关着的，所有的线程都在此等待，直到终止状态，所有线程才可通过。闭锁的终止状态结束后，状态就不会
 * 再改变了，将一直保持开启。这一点和栅栏是不同的。
 * 对于countDownLatch的终止状态是在count=0时
 * Created by Administrator on 2016/7/6 0006.
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        Random random = new Random();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CountDownLatch order = new CountDownLatch(1);
        CountDownLatch answer = new CountDownLatch(3);
        for(int i=0;i<3;i++){
            executorService.execute(()->{

                try {
                    System.out.println("线程：" + Thread.currentThread().getName() + "正在等待命令");
                    order.await();
                    System.out.println("线程：" + Thread.currentThread().getName() + "已收到命令，正在执行");
                    Thread.sleep(random.nextInt(10000));
                    System.out.println("线程：" + Thread.currentThread().getName() + "执行完毕，正在返回结果");
                    answer.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });

        }

        try {
            Thread.sleep(10000);
            System.out.println("线程：" + Thread.currentThread().getName() + "已发布命令，等待执行结果");
            order.countDown();

            answer.await();
            System.out.println("线程："+Thread.currentThread().getName()+"已收到执行结果");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
