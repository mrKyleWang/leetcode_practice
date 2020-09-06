package math;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * 60. 第k个排列
 * @author KyleWang
 * @version 1.0
 * @date 2020年09月06日
 */
public class PermutationSequence {

    /*
        给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
        按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
        "123"
        "132"
        "213"
        "231"
        "312"
        "321"
        给定 n 和 k，返回第 k 个排列。

        说明：
            给定 n 的范围是 [1, 9]。
            给定 k 的范围是[1,  n!]。
     */

    @Test
    public void test() {
        Assert.assertEquals("213", getPermutation(3, 3));
    }

    @Test
    public void test2() {
        Assert.assertEquals("2314", getPermutation(4, 9));
    }

    public String getPermutation(int n, int k) {
        List<Integer> nums = new LinkedList<>();
        int[] factorial = new int[n];
        for (int i = 0; i < n; i++) {
            factorial[i] = i == 0 ? 1 : factorial[i - 1] * (i + 1);
            nums.add(i + 1);
        }

        StringBuilder builder = new StringBuilder();
        for (int i = n - 1; i > 0; i--) {
            int interval = factorial[i - 1];
            int index = (k + interval - 1) / interval;
            k = k - ((index - 1) * interval);
            index = index == 0 ? 0 : index - 1;
            builder.append(nums.get(index));
            nums.remove(index);
        }
        builder.append(nums.get(0));
        return builder.toString();
    }
}
