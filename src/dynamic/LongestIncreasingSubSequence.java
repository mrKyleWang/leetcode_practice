package dynamic;

/**
 * 300. 最长上升子序列
 * @author KyleWang
 * @version 1.0
 * @date 2019/11/26
 */
public class LongestIncreasingSubSequence {

    /*
        给定一个无序的整数数组，找到其中最长上升子序列的长度。

        示例:
            输入: [10,9,2,5,3,7,101,18]
            输出: 4
            解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。

        说明:
            可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
            你算法的时间复杂度应该为 O(n2) 。
        进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
     */

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(new LongestIncreasingSubSequence().lengthOfLIS(nums));
    }

    public int lengthOfLIS(int[] nums) {
        // 保存取到每个位置的数时，当前最长序列长度
        if (nums.length > 0) {
            int[] tempMax = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                int currentMax = 0;
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i] && tempMax[j] > currentMax) {
                        currentMax = tempMax[j];
                    }
                }
                tempMax[i] = currentMax + 1;
            }
            return max(tempMax);
        }
        return 0;
    }

    private int max(int... nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = nums[i] > max ? nums[i] : max;
        }
        return max;
    }
}
