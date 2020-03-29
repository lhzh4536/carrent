package lhzh;

import org.junit.Test;

public class StringReplaceTest {

    @Test
    public void Test1_Replcace(){
        String carimg="aaa.txt";
        String suffix ="aaa";
        String replace = carimg.replace(suffix, "bbb");
        System.out.println(replace);
    }

}
