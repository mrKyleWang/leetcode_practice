package array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 283. 移动零
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月15日
 */
public class MoveZeroes {

    /*
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 示例: 输入: [0,1,0,3,12] 输出:
     * [1,3,12,0,0] 说明: 必须在原数组上操作，不能拷贝额外的数组。 尽量减少操作次数。
     */

    @Test
    public void test() {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (index != i) {
                    nums[index] = nums[i];
                    nums[i] = 0;
                }
                index++;
            }
        }
    }
}
