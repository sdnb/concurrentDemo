package demo13;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程同步通信 新
 * 笔试题：
 * 子线程执行10，主线程执行100次，子线程执行10次，主线程执行100次，如此循环执行50次
 * Created by Administrator on 2016/7/3 0003.
 */
public class ThreadComunication {
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
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        boolean isShouldSub = true;

        public void sub(int i){
            lock.lock();
            try{
                while (!isShouldSub){
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for(int j=0;j<10;j++){
                    System.out.println("sub thread squence:"+(j+1)+",loop:"+(i+1));
                }
                isShouldSub = false;
                condition.signal();
            }finally {
                lock.unlock();
            }

        }

        public void main(int i){
            lock.lock();
            try {
                while (isShouldSub){
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for(int j=0;j<100;j++){
                    System.out.println("main thread squence:"+(j+1)+",loop:"+(i+1));
                }
                isShouldSub = true;
                condition.signal();
            }finally {
                lock.unlock();
            }

        }
    }
}
