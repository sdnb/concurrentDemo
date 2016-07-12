package demo10;

import java.util.Random;
import java.util.concurrent.*;

/**
 * callable和runable区别是可以返回线程执行的结果
 * Created by Administrator on 2016/7/4 0004.
 */
public class FutureAndCallableTest {
    public static void main(String[] args) {
        //通过executor提交任务
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(0500);
                Random random = new Random();
                return random.nextInt(1000);
            }
        });
        try {
            System.out.println(future.get(1,TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }


        //普通方式提交任务
        Callable<Integer> data = new Callable<Integer>(){
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        };
        FutureTask<Integer> futureTask = new FutureTask<Integer>(data);
        new Thread(futureTask).start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
