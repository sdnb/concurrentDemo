package demo7;

/**
 * 多线程共享数据
 * 以面试题举例：创建四个线程 2个线程每次对j加1 ，另外两个线程；每次对j减一
 * Created by Administrator on 2016/7/3 0003.
 */
public class MultiThreadSharedData {
    static ShareData data = new ShareData();
    public static void main(String[] args) {
        new Thread(() ->data.dec()).start();
        new Thread(() ->data.inc()).start();
    }
}

class ShareData{
    private int  j = 0;
    public synchronized void inc(){
        j++;
        System.out.println("inc runnable:" + Thread.currentThread().getName() + ",j=" + j);
    }
    public synchronized void dec(){
        j--;
        System.out.println("dec runnable:" + Thread.currentThread().getName() + ",j=" + j);
    }
}
