package demo7;

/**
 * Created by Administrator on 2016/7/3 0003.
 */
public class SaleTicketThread {
    int count = 20;
    public static void main(String[] args) {
       new SaleTicketThread().excute();
    }

    public void excute(){
        new Thread(() -> {while (count>0) sale();}).start();
        new Thread(() -> {while (count>0) sale();}).start();

    }

    public synchronized void sale(){

            count--;
            System.out.println("current saler thread："+Thread.currentThread().getName()+",还剩："+count+"张");

    }
}




