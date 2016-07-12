package demo06;

import java.util.Random;

/**
 * ThreadLocal类使用技巧2 多个变量
 * Created by Administrator on 2016/7/3 0003.
 */
public class ThreadLocalTest1 {

    public static void main(String[] args) {

        for(int i=0;i<2;i++){
            int data = new Random().nextInt();
            System.out.println(data);
            new Thread(() -> {
                Data data1 = Data.getThreadDataInstance();
                data1.setName("name"+data);
                data1.setAge(data);
                System.out.println("Thread:" + Thread.currentThread().getName() + " put data age:" + data + " name:name" + data);
                new A().getData();
                new B().getData();
            }).start();
        }

    }

    static class A{
        public void getData(){
            System.out.println("Class A of Thread:"+Thread.currentThread().getName()+" get data age="+ Data.getThreadDataInstance().getAge()+",name="+Data.getThreadDataInstance().getName());
        }
    }

    static class B{
        public void getData(){
            System.out.println("Class A of Thread:"+Thread.currentThread().getName()+" get data age="+ Data.getThreadDataInstance().getAge()+",name="+Data.getThreadDataInstance().getName());
        }
    }

    private static class Data{
        private String name;
        private int age;
        private static Data data = null;
        private static ThreadLocal<Data> map = new ThreadLocal<>();

        public static Data getThreadDataInstance(){
            data = map.get();
            if( data == null){
                data = new Data();
                map.set(data);
            }
            return data;
        }
        private Data(){}


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
