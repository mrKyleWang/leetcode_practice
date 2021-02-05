package array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1208. 尽可能使字符串相等
 * @author KyleWang
 * @version 1.0
 * @date 2021年02月05日
 */
public class GetEqualSubstringsWithinBudget {

    @Test
    public void test() {
        String s = "krrgw";
        String t = "zjxss";
        Assert.assertEquals(2, equalSubstring(s, t, 19));
    }

    public int equalSubstring(String s, String t, int maxCost) {
        int l = 0;
        int r = 0;
        int cost = 0;
        while (r < s.length()) {
            cost += Math.abs(s.charAt(r) - t.charAt(r));
            r++;
            if (cost > maxCost) {
                cost -= Math.abs(s.charAt(l) - t.charAt(l));
                l++;
            }
        }
        return r - l;
    }
}
