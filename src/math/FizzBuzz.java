package math;

import java.util.LinkedList;
import java.util.List;

/**
 * 412. Fizz Buzz
 * @author KyleWang
 * @version 1.0
 * @date 2019年08月09日
 */
public class FizzBuzz {

    /*
        写一个程序，输出从 1 到 n 数字的字符串表示。

        1. 如果 n 是3的倍数，输出“Fizz”；

        2. 如果 n 是5的倍数，输出“Buzz”；

        3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
    */

    public static void main(String[] args) {
        System.out.println(fizzBuzz(15));
    }

    public static List<String> fizzBuzz(int n) {
        LinkedList<String> list = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0) {
                if (i % 5 == 0) {
                    list.add("FizzBuzz");
                } else {
                    list.add("Fizz");
                }
            } else if (i % 5 == 0) {
                list.add("Buzz");
            } else {
                list.add(i + "");
            }
        }
        return list;
    }
}
