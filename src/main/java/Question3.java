/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class Question3 extends Thread{
    private TestDo testDo;
    private String key;
    private String value;

    public Question3(String key,String key2,String value){
        this.testDo = TestDo.getInstance();
			/*常量"1"和"1"是同一个对象，下面这行代码就是要用"1"+""的方式产生新的对象，
			以实现内容没有改变，仍然相等（都还为"1"），但对象却不再是同一个的效果*/
        this.key = key+key2;
        this.value = value;
    }


    public static void main(String[] args) throws InterruptedException{
        Question3 a = new Question3("1","","1");
        Question3 b = new Question3("1","","2");
        Question3 c = new Question3("3","","3");
        Question3 d = new Question3("4","","4");
        System.out.println("begin:"+(System.currentTimeMillis()/1000));
        a.start();
        b.start();
        c.start();
        d.start();

    }

    @Override
    public void run(){
        testDo.doSome(key, value);
    }

    private static class TestDo {

        private TestDo() {}
        private static TestDo _instance = new TestDo();
        public  static TestDo getInstance() {
            return _instance;
        }

        public void doSome(Object key, String value) {

            // 以大括号内的是需要局部同步的代码，不能改动!
            {
                try {
                    Thread.sleep(1000);
                    System.out.println(key+":"+value + ":"
                            + (System.currentTimeMillis() / 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

