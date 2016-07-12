package demo06;

import java.io.File;

/**
 * Created by Administrator on 2016/7/3 0003.
 */
public class JvmTest {
    public static void main(String[] args) throws InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        runtime.addShutdownHook(new Thread(() -> {
            System.out.println("dfasdfsaf");
        }));

    }
}
