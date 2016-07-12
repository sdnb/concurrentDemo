package demo06;

import java.util.Random;

/**
 * ThreadLocal类使用技巧2
 * Created by Administrator on 2016/7/3 0003.
 */
public class ThreadLocalTest {

    private static ThreadLocal<Integer> x = new ThreadLocal<>();
    public static void main(String[] args) {

        for(int i=0;i<2;i++){
            int data = new Random().nextInt();
            System.out.println(data);
            new Thread(() -> {
                x.set(data);
                System.out.println("Thread:"+Thread.currentThread().getName()+" put data "+data);
                new A().getData();
                new B().getData();
            }).start();
        }

    }

    static class A{
        public void getData(){
            System.out.println("Class A of Thread:"+Thread.currentThread().getName()+" get data:"+ x.get());
        }
    }

    static class B{
        public void getData(){
            System.out.println("Class B of Thread:"+Thread.currentThread().getName()+" get data:"+ x.get());
        }
    }
}
