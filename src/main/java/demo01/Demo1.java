package demo01;

/**
 * Created by Administrator on 2016/7/3 0003.
 */
public class Demo1 {
    public static void main(String[] args) {
        Thread thread  = new Thread(){
            @Override
            public void run(){
                while (true){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程名0："+Thread.currentThread().getName());
//                    System.out.println("线程名："+this.getName());
                }
            }
        };
//        thread.start();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程名1："+Thread.currentThread().getName());
                }
            }
        });
//        thread1.start();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程名2："+Thread.currentThread().getName());
                }
            }
        };
       Thread thread2 = new Thread(runnable) {
           @Override
           public void run() {
               while (true) {
                   try {
                       Thread.sleep(500);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   System.out.println("线程名3：" + Thread.currentThread().getName());
               }
           }
       };
//        thread2.start();

        Runnable thread3 = new Thread(){
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread3：" + Thread.currentThread().getName());
                }
            }
        };

        Thread thread4 = new Thread(){
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread4：" + Thread.currentThread().getName());
                }
            }
        };

        thread4.run();
        thread3.run();

//        new Thread(runnable).start();
//        runnable.run();
    }



}
