package demo02;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/7/3 0003.
 */
public class TraditionalTimerTest {
    public static void main(String[] args) {

        //定时炸弹
//        new Timer().schedule(new TimerTask() {
//            int n=10;
//            @Override
//            public void run() {
//                System.out.println("倒计时："+(n--));
//                if(n==0){
//                    System.out.println("bombing.....");
//                    this.cancel();
//                }
//            }
//        },100,1000);


//        //连环炸弹 每个2s炸一次
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//
//                System.out.println("bombing...");
//
//            }
//        }, 1000, 2000);


        //子母炸弹  每次爆炸后触发新的爆炸
        new Timer().schedule(new MyTimerTask(),2000);

        while (true){
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread:" + Thread.currentThread().getName() + ",秒数：" + new Date().getSeconds());
        }

    }

    static class MyTimerTask extends TimerTask{
        private static int counter = 0;
        @Override
        public void run() {
            counter = (counter+1)%2;
            System.out.println("Thread:" + Thread.currentThread().getName()+",job:bombing...");
            new Timer().schedule(new MyTimerTask(), counter*2000+2000);
        }
    }
}
