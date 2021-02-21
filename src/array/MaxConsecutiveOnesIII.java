package array;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

/**
 * 1004. 最大连续1的个数 III
 * @author KyleWang
 * @version 1.0
 * @date 2021年02月19日
 */
public class MaxConsecutiveOnesIII {

    /*
        给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
        返回仅包含 1 的最长（连续）子数组的长度。

        示例 1：
            输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
            输出：6
            解释：
            [1,1,1,0,0,1,1,1,1,1,1]
            粗体数字从 0 翻转到 1，最长的子数组长度为 6。
        示例 2：
            输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
            输出：10
            解释：
            [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
            粗体数字从 0 翻转到 1，最长的子数组长度为 10。
         
        提示：
            1 <= A.length <= 20000
            0 <= K <= A.length
            A[i] 为 0 或 1 
     */


    @Test
    public void test() {
        int[] A = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        Assert.assertEquals(6, longestOnes(A, 2));
    }

    @Test
    public void test2() {
        int[] A = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        Assert.assertEquals(10, longestOnes(A, 3));
    }

    public int longestOnes(int[] A, int K) {
        LinkedList<Integer> queue = new LinkedList<>();
        int n = A.length;
        int left = 0;
        int right = 0;
        int max = 0;
        while (right < n) {
            if (A[right] == 0) {
                queue.offer(right);
                if (queue.size() > K) {
                    left = queue.pollFirst() + 1;
                }
            }
            max = Math.max(max, right - left + 1);
            right++;

        }
        return max;
    }
}
