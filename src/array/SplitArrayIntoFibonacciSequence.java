package array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 842. 将数组拆分成斐波那契序列
 * @author KyleWang
 * @version 1.0
 * @date 2020年12月08日
 */
public class SplitArrayIntoFibonacciSequence {


    /*
        给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。
        形式上，斐波那契式序列是一个非负整数列表 F，且满足：
        0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
        F.length >= 3；
        对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
        另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
        返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。

         

        示例 1：
            输入："123456579"
            输出：[123,456,579]
        示例 2：
            输入: "11235813"
            输出: [1,1,2,3,5,8,13]
        示例 3：
            输入: "112358130"
            输出: []
            解释: 这项任务无法完成。
        示例 4：
            输入："0123"
            输出：[]
            解释：每个块的数字不能以零开头，因此 "01"，"2"，"3" 不是有效答案。
        示例 5：
            输入: "1101111"
            输出: [110, 1, 111]
            解释: 输出 [11,0,11,11] 也同样被接受。
         
        提示：
            1 <= S.length <= 200
            字符串 S 中只含有数字。
     */

    @Test
    public void test() {
        String S = "123456579";
        System.out.println(splitIntoFibonacci(S));
    }

    @Test
    public void test2() {
        String S = "11235813";
        System.out.println(splitIntoFibonacci(S));
    }

    @Test
    public void test3() {
        String S = "112358130";
        System.out.println(splitIntoFibonacci(S));
    }

    @Test
    public void test4() {
        String S = "0123";
        System.out.println(splitIntoFibonacci(S));
    }

    @Test
    public void test5() {
        String S = "1101111";
        System.out.println(splitIntoFibonacci(S));
    }

    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> result = new ArrayList<>();
        return dfs(S.toCharArray(), result, 0) ? result : new ArrayList<>();
    }

    private boolean dfs(char[] chars, List<Integer> list, int cur) {
        int size = list.size();
        if (cur == chars.length) {
            return list.size() > 2;
        }
        int num = 0;
        for (int i = cur; i < chars.length; i++) {
            num = num * 10 + (chars[i] - '0');
            if (num < 0) {
                return false;
            }
            if (size < 2 || num == list.get(size - 1) + list.get(size - 2)) {
                list.add(num);
                if (dfs(chars, list, i + 1)) {
                    return true;
                }
                list.remove(size);
            } else if (num > list.get(size - 1) + list.get(size - 2)) {
                return false;
            }

            if (chars[i] == '0' && i == cur) {
                return false;
            }
        }
        return false;
    }

}
