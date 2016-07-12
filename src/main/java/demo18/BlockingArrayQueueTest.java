package demo18;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class BlockingArrayQueueTest {
    public static void main(String[] args) {
        BlockingQueue blockingQueue = new ArrayBlockingQueue<Integer>(3);
        new Thread(()->{
            try {
                while (true){
                    Thread.sleep(2000);
                    System.out.println("线程：" + Thread.currentThread().getName() + "准备放数据");
                    blockingQueue.put(1);
                    System.out.println("线程："+Thread.currentThread().getName()+"已放入数据，队列中目前有"+blockingQueue.size()+"个数据");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
        new Thread(()->{
            try {
                while (true){
                    Thread.sleep(1000);
                    System.out.println("线程：" + Thread.currentThread().getName() + "准备取数据");
                    blockingQueue.take();
                    System.out.println("线程："+Thread.currentThread().getName()+"已取走数据，队列中目前有"+blockingQueue.size()+"个数据");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
