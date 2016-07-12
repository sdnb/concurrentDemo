package demo10;

import java.util.concurrent.*;

/**
 * 接收多个线程返回结果
 * Created by Administrator on 2016/7/4 0004.
 */
public class FutureAndCallableTest1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(executorService); //
        for(int i=0;i<10;i++){
            final int taskId =i;
            cs.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+" 正在执行。。。");
                    return taskId;
                }
            });
        }

        for(int i=0;i<20;i++){
            try {
                System.out.println(Thread.currentThread().getName()+"正在取值："+cs.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
