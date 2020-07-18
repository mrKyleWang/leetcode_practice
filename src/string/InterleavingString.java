package string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 97. 交错字符串
 * @author KyleWang
 * @version 1.0
 * @date 2020年07月18日
 */
public class InterleavingString {

    /*
        给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
        示例 1:
            输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
            输出: true
        示例 2:
            输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
            输出: false

     */

    @Test
    public void test() {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        Assert.assertEquals(true, isInterleave(s1, s2, s3));
    }

    @Test
    public void test2() {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbbaccc";
        Assert.assertEquals(false, isInterleave(s1, s2, s3));
    }

    @Test
    public void test3() {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbbaccc";
        Assert.assertEquals(false, isInterleave(s1, s2, s3));
    }

    /**
     * 回溯
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length()+s2.length()!=s3.length()){
            return false;
        }
        return isInterleave(s1, s2, s3, 0, 0, 0);
    }

    public boolean isInterleave(String s1, String s2, String s3, int i1, int i2, int i3) {
        for (int i = i3; i < s3.length(); i++) {
            char c = s3.charAt(i);
            if (i1 < s1.length() && s1.charAt(i1) == c) {
                if (isInterleave(s1, s2, s3, i1 + 1, i2, i + 1)) {
                    return true;
                }
            }
            if (i2 < s2.length() && s2.charAt(i2) == c) {
                if (isInterleave(s1, s2, s3, i1, i2 + 1, i + 1)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
