package demo03;

/**
 * 线程互斥
 * Created by Administrator on 2016/7/3 0003.
 */
public class TraditionalThreadSynchronized {
    private static Outputer outputer = new Outputer();
    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                  outputer.output3("china");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    outputer.output3("American");
            }
        });
        thread1.start();
        thread2.start();
    }

     static class Outputer{
        public void output(String name){

            synchronized (Outputer.class){
                for(int i=0;i<name.length();i++){
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            }
        }

        public static synchronized void output1(String name){
                for(int i=0;i<name.length();i++){
                    System.out.print(name.charAt(i));
                }
                System.out.println();

        }
         public synchronized void output2(String name){

                 for(int i=0;i<name.length();i++){
                     System.out.print(name.charAt(i));
                 }
                 System.out.println();
         }
         public synchronized void output3(String name){
                 for(int i=0;i<name.length();i++){
                     System.out.print(name.charAt(i));
                 }
                 System.out.println();

         }
    }
}
