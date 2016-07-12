package demo09;

import java.util.concurrent.*;

/**
 * java并发库及使用
 * Created by Administrator on 2016/7/4 0004.
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        final int count ;
//        ExecutorService threadpool = Executors.newFixedThreadPool(3);
        ExecutorService threadpool = Executors.newCachedThreadPool();
//        ExecutorService threadpool = Executors.newSingleThreadExecutor();
//        for(int i=0;i<10;i++){
//            final int task=i;
//            threadpool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    for (int j = 0; j < 10; j++) {
//                        try {
//                            Thread.sleep(10);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println("thread : " + Thread.currentThread().getName() + " task:" + task + ",loop:" + j);
//                    }
//                }
//            });
//        }
//
//        threadpool.shutdown();

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        //10秒后执行
//        executorService.schedule(()->{
//            System.out.println("pipi....");
//        },10, TimeUnit.SECONDS);
        //10s后执行，然后每个两秒执行一次

        executorService.scheduleAtFixedRate(()->{
            System.out.println("pipi....");
        },10,2,TimeUnit.SECONDS);

    }
}
