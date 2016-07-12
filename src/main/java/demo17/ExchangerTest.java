package demo17;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class ExchangerTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Exchanger exchanger = new Exchanger();
        Random random = new Random();
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                String data = "狸猫";
                try {
                    System.out.println("线程" + Thread.currentThread().getName() + "到达，准备交换数据");
                    Thread.sleep(random.nextInt(10000));
                    String data1 = (String) exchanger.exchange(data);
                    System.out.println("线程" + Thread.currentThread().getName() +"原为："+data+ "，交换后成了：" + data1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                String data = "太子";
                try {
                    System.out.println("线程" + Thread.currentThread().getName() + "到达，准备交换数据");
                    Thread.sleep(random.nextInt(10000));
                    String data1 = (String) exchanger.exchange(data);
                    System.out.println("线程" + Thread.currentThread().getName() +"原为："+data+ "，交换后成了：" + data1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        executorService.execute(r1);
        executorService.execute(r2);
        executorService.shutdown();
    }
}
