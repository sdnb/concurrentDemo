package demo7;

/**
 * 面试题
 * 创建四个线程 2个线程每次对j加1 ，另外两个线程；每次对j减一
 * Created by Administrator on 2016/7/3 0003.
 */
public class Demo7 {
    int j = 0;
    public static void main(String[] args) {
        Demo7 demo7 = new Demo7();
//        for(int i=0;i<2;i++){
          new Thread(demo7 .new DecRunnable()).start();
          new Thread(demo7 .new IncRunnable()).start();
//        }

    }

    class DecRunnable implements Runnable{

        @Override
        public void run() {
                dec();
        }
    }

    class IncRunnable implements Runnable{
        @Override
        public void run() {
            inc();
        }
    }

    public synchronized void inc(){
        j++;
        System.out.println("dec runnable:" + Thread.currentThread().getName() + ",j=" + j);
    }

    public synchronized void dec(){
        j--;
        System.out.println("inc runnable:"+Thread.currentThread().getName()+",j="+j);
    }
}
