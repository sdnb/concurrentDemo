package demo05;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 线程范围内变量共享
 *
 * Created by Administrator on 2016/7/3 0003.
 */
public class ThreadShareData {
//    private static int data = 0;

    private static Map<Thread,Integer> dataMap = new HashMap<>();
    public static void main(String[] args) {

        for(int i=0;i<2;i++){
             int data = new Random().nextInt();
            System.out.println(data);
            new Thread(() -> {
                dataMap.put(Thread.currentThread(), data);
                System.out.println("Thread:"+Thread.currentThread().getName()+" put data "+data);
                new A().getData();
                new B().getData();
            }).start();
        }

    }

    static class A{
        public void getData(){
            System.out.println("Class A of Thread:"+Thread.currentThread().getName()+" get data:"+ dataMap.get(Thread.currentThread()));
        }
    }

    static class B{
        public void getData(){
            System.out.println("Class B of Thread:"+Thread.currentThread().getName()+" get data:"+ dataMap.get(Thread.currentThread()));
        }
    }
}
