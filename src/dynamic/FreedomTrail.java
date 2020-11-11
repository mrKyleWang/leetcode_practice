package dynamic;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 514. 自由之路
 * @author KyleWang
 * @version 1.0
 * @date 2020年11月11日
 */
public class FreedomTrail {

    /*
        电子游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门。
        给定一个字符串 ring，表示刻在外环上的编码；给定另一个字符串 key，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
        最初，ring 的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完 key 中的所有字符。
        旋转 ring 拼出 key 字符 key[i] 的阶段中：
            您可以将 ring 顺时针或逆时针旋转一个位置，计为1步。
            旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于字符 key[i] 。
            如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
        示例：
                     G
                 G       O

                 N       D

                   I   D
             
            输入: ring = "godding", key = "gd"
            输出: 4
            解释:
                 对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。
                 对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
                 当然, 我们还需要1步进行拼写。
                 因此最终的输出是 4。
        提示：
            ring 和 key 的字符串长度取值范围均为 1 至 100；
            两个字符串中都只有小写字符，并且均可能存在重复字符；
            字符串 key 一定可以由字符串 ring 旋转拼出。
     */


    @Test
    public void test() {
        String ring = "godding";
        String key = "gd";
        Assert.assertEquals(4, findRotateSteps(ring, key));
    }

    @Test
    public void test2() {
        String ring = "pqwcx";
        String key = "cpqwx";
        Assert.assertEquals(13, findRotateSteps(ring, key));
    }


    /**
     * dp[i][j] 标识key的第i个字符，ring的第j个字符与 12:00方向对其，最少需要多少步
     * pos 保存每个字符对应在ring中的位置（可能会有多个）
     */
    public int findRotateSteps(String ring, String key) {
        char[] ringChars = ring.toCharArray();
        char[] keyChars = key.toCharArray();

        int[][] dp = new int[keyChars.length][ringChars.length];
        List<Integer>[] pos = new List[26];
        for (int i = 0; i < ringChars.length; i++) {
            char c = ringChars[i];
            List<Integer> list = pos[c - 'a'];
            if (list == null) {
                list = new ArrayList<>();
                pos[c - 'a'] = list;
            }
            list.add(i);
        }
        for (int i = 0; i < dp.length; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        char first = keyChars[0];
        for (Integer ringIndex : pos[first - 'a']) {
            dp[0][ringIndex] = Math.min(ringIndex, ringChars.length - ringIndex);
        }
        for (int i = 1; i < keyChars.length; i++) {
            for (Integer cur : pos[keyChars[i] - 'a']) {
                int min = Integer.MAX_VALUE;
                for (Integer pre : pos[keyChars[i - 1] - 'a']) {
                    min = Math.min(Math.min(Math.abs(cur - pre), ringChars.length - Math.abs(cur - pre)) + dp[i - 1][pre], min);
                }
                dp[i][cur] = min;
            }
        }
        int result = Integer.MAX_VALUE;
        for (int tempMin : dp[keyChars.length - 1]) {
            result = Math.min(result, tempMin);
        }
        return result + keyChars.length;
    }
}
