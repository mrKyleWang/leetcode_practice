package array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 349. 两个数组的交集
 * @author KyleWang
 * @version 1.0
 * @date 2020年11月02日
 */
public class IntersectionOfTwoArrays {

    /*
        给定两个数组，编写一个函数来计算它们的交集。
        示例 1：
            输入：nums1 = [1,2,2,1], nums2 = [2,2]
            输出：[2]
        示例 2：
            输入：nums1 = [4], nums2 = [9,4,9,8,4]
            输出：[9,4],9,5
        说明：
            输出结果中的每个元素一定是唯一的。
            我们可以不考虑输出结果的顺序。
     */

    @Test
    public void test() {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println(Arrays.toString(intersection(nums1, nums2)));
    }

    @Test
    public void test2() {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};
        System.out.println(Arrays.toString(intersection(nums1, nums2)));
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        for (int i : nums2) {
            if (set.contains(i)) {
                list.add(i);
                set.remove(i);
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

}
