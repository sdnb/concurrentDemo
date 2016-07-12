package demo04;

/**
 * 线程同步通信
 * 笔试题：
 * 子线程执行10，主线程执行100次，子线程执行10次，主线程执行100次，如此循环执行50次
 * Created by Administrator on 2016/7/3 0003.
 */
public class TraditionalThreadComunication {
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
        boolean isShouldSub = true;

        public synchronized void sub(int i){
            while (!isShouldSub){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for(int j=0;j<10;j++){
                System.out.println("sub thread squence:"+(j+1)+",loop:"+(i+1));
            }
            isShouldSub = false;
            this.notify();
        }

        public synchronized void main(int i){
            while (isShouldSub){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for(int j=0;j<100;j++){
                System.out.println("main thread squence:"+(j+1)+",loop:"+(i+1));
            }
            isShouldSub = true;
            this.notify();
        }
    }
}
