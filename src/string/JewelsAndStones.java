package string;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

/**
 * 771. 宝石与石头
 * @author KyleWang
 * @version 1.0
 * @date 2020年10月02日
 */
public class JewelsAndStones {

    /*
         给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。
         S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
        J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。

        示例 1:

        输入: J = "aA", S = "aAAbbbb"
        输出: 3
        示例 2:

        输入: J = "z", S = "ZZ"
        输出: 0
        注意:

        S 和 J 最多含有50个字母。
         J 中的字符不重复。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/jewels-and-stones
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    @Test
    public void test() {
        String J = "aA";
        String S = "aAAbbbb";
        Assert.assertEquals(3, numJewelsInStones(J, S));
    }

    @Test
    public void test2() {
        String J = "z";
        String S = "ZZ";
        Assert.assertEquals(0, numJewelsInStones(J, S));
    }

    public int numJewelsInStones(String J, String S) {
        int count = 0;
        HashSet<Character> set = new HashSet<>(J.length());
        for (char c : J.toCharArray()) {
            set.add(c);
        }
        for (char c : S.toCharArray()) {
            if (set.contains(c)) {
                count++;
            }
        }
        return count;
    }
}
