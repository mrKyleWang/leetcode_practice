package array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 229. 求众数 II
 * @author KyleWang
 * @version 1.0
 * @date 2021年03月05日
 */
public class MajorityElementII {

    /*
        给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
        进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。

        示例 1：
            输入：[3,2,3]
            输出：[3]
        示例 2：
            输入：nums = [1]
            输出：[1]
        示例 3：
            输入：[1,1,1,3,3,2,2,2]
            输出：[1,2]
         
        提示：
            1 <= nums.length <= 5 * 104
            -109 <= nums[i] <= 109
     */
    @Test
    public void test() {
        System.out.println(majorityElement(new int[]{3, 2, 3}));
    }

    @Test
    public void test2() {
        System.out.println(majorityElement(new int[]{1}));
    }

    @Test
    public void test3() {
        System.out.println(majorityElement(new int[]{1, 1, 1, 3, 3, 2, 2, 2}));
    }

    @Test
    public void test4() {
        System.out.println(majorityElement(new int[]{2, 1, 1, 3, 1, 4, 5, 6}));
    }

    /**
     * 摩尔投票法，相当于每次维持一个k-1的候选人，当前遍历到的数在候选人中时，票数++，否则如果候选人不满k-1，则成为候选人，票数=1
     * 当既不在候选人中，且候选人已满，则将全部候选人的票数都-1
     * <p>
     * 参考求众数中的两两消除，是同一种思想
     * {@link array.MajorityElement}
     */
    public List<Integer> majorityElement(int[] nums) {
        int candidate1 = 0, hp1 = 0;
        int candidate2 = 0, hp2 = 0;
        for (int num : nums) {
            if (hp1 != 0 && num == candidate1) {
                hp1++;
            } else if (hp2 != 0 && num == candidate2) {
                hp2++;
            } else if (hp1 == 0) {
                candidate1 = num;
                hp1 = 1;
            } else if (hp2 == 0) {
                candidate2 = num;
                hp2 = 1;
            } else {
                hp1--;
                hp2--;
            }
        }

        List<Integer> res = new ArrayList<>();
        int count1 = 0;
        int count2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            }
        }
        if (count1 > nums.length / 3) {
            res.add(candidate1);
        }
        if (count2 > nums.length / 3) {
            res.add(candidate2);
        }
        return res;
    }
}
