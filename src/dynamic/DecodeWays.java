package dynamic;

import org.junit.Assert;
import org.junit.Test;

/**
 * 91. 解码方法
 * @author KyleWang
 * @version 1.0
 * @date 2021年04月21日
 */
public class DecodeWays {

    /*
        一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
        'A' -> 1
        'B' -> 2
        ...
        'Z' -> 26
        要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：

        "AAJF" ，将消息分组为 (1 1 10 6)
        "KJF" ，将消息分组为 (11 10 6)
        注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。

        给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
        题目数据保证答案肯定是一个 32 位 的整数。
       。
     */

    @Test
    public void test() {
        Assert.assertEquals(2, numDecodings("11106"));
    }

    public int numDecodings(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            int c = 0;
            // 单独数字
            if (chars[i] != '0') {
                c = i > 0 ? dp[i - 1] : 1;
            } else if (i > 0 && chars[i - 1] > '2') {
                return 0;
            }
            // 能与前面一个组合
            if (i > 0 && (chars[i - 1] == '1' || (chars[i - 1] == '2' && chars[i] <= '6'))) {
                c += i > 1 ? dp[i - 2] : 1;
            }
            dp[i] = c;
        }
        return dp[n - 1];
    }
}
