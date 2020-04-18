package array;

import org.junit.Test;

/**
 * 11. 盛最多水的容器
 * @author KyleWang
 * @version 1.0
 * @date 2020年04月18日
 */
public class ContainerWithMostWater {


    /*
        给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
        在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
        找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

        说明：你不能倾斜容器，且 n 的值至少为 2。


        示例：
            输入：[1,8,6,2,5,4,8,3,7]
            输出：49
            最大容量容器为 8~7这段，容量为7*7

     */


    @Test
    public void test() {
//        System.out.println(maxArea(new int[]{1, 8, 6, 2, 9, 4, 8, 3, 7}));
        System.out.println(maxArea(new int[]{2, 3, 4, 5, 18, 17, 6}));
    }

    /**
     * 使用双指针，由于面积是由指针中数值小的与距离的乘积，如果指针移动，则距离一定减小，
     * 要想乘积可能增大，则只有使两个指针数值的最小值增大，也就是移动数值较小的指针
     */
    public int maxArea(int[] height) {
        int head = 0;
        int tail = height.length - 1;
        int max = 0;
        while (head <= tail) {
            max = Math.max(max, Math.min(height[head], height[tail]) * (tail - head));
            if (height[head] < height[tail]) {
                head++;
            } else {
                tail--;
            }
        }
        return max;
    }

}
