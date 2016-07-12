package demo19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class ConcurrentColletionTest {
    public static void main(String[] args) {

        List<Integer> list =new CopyOnWriteArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        for(Integer i : list){
            if(i==3){
                list.remove(i);
            }
            System.out.println(list);
        }
    }
}
