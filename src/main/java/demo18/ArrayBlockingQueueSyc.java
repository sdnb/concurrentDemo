package demo18;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 用两个具有1个空间的队列来实现同步通信
 * Created by Administrator on 2016/7/6 0006.
 */
public class ArrayBlockingQueueSyc {
    public static void main(String[] args) throws InterruptedException {
        Business business = new Business();
        new Thread(() ->{
            for(int i=0;i<50;i++){
                business.sub(i);
            }
        }).start();

        new Thread(() ->{
            for(int i=0;i<50;i++){
                business.main(i);
            }
        }).start();
    }



    private static class Business{
        BlockingQueue<Integer> blockingQueue1 = new ArrayBlockingQueue<Integer>(1);
        BlockingQueue<Integer> blockingQueue2 = new ArrayBlockingQueue<Integer>(1);
        {
            try {
                blockingQueue1.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public  void sub(int i){
            try {
                blockingQueue1.put(1);
                for(int j=0;j<10;j++){
                    System.out.println("sub thread squence:"+(j+1)+",loop:"+(i+1));
                }
                blockingQueue2.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public  void main(int i){

            try {
                blockingQueue2.put(1);
                for(int j=0;j<100;j++){
                    System.out.println("main thread squence:"+(j+1)+",loop:"+(i+1));
                }
                blockingQueue1.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
