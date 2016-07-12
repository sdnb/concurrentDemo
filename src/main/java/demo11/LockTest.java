package demo11;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class LockTest {
    private static Outputer outputer = new Outputer();
    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    outputer.output("china");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    outputer.output("American");
            }
        });
        thread1.start();
        thread2.start();
    }

    static class Outputer{
        Lock lock = new ReentrantLock();
        public void output(String name){


            try {
                lock.lock();
                for(int i=0;i<name.length();i++){
                    System.out.print(name.charAt(i));
                }
                System.out.println();
                String a = null;
                a.length();
                lock.unlock();
            }catch (Exception e){

            }

        }
    }
}
