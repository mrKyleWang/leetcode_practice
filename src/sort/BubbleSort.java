package sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 冒泡排序
 * @author KyleWang
 * @version 1.0
 * @date 2021年01月28日
 */
public class BubbleSort {

    @Test
    public void test() {
        int[] nums = {63, 57, 70, 50, 25, 83, 41, 22, 59, 47, 4, 20, 55, 27, 36, 64, 58, 74, 96, 38, 67, 86, 72, 97, 6, 15, 35, 93, 34, 24, 53, 88, 32, 69, 56, 84, 44, 71, 49, 14, 9, 82, 31, 79, 68, 13, 3, 65, 8, 43, 23, 66, 98, 77, 26, 90, 51, 39, 60, 17, 85, 46, 0, 10, 73, 92, 33, 42, 21, 19, 12, 89, 87, 18, 91, 62, 1, 2, 40, 80, 45, 7, 37, 48, 11, 81, 5, 30, 95, 75, 54, 94, 29, 78, 52, 76, 16, 99, 28, 61};
        bubbleSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 每次都把最小的往上冒
     * 使用count优化，如果在一次轮询中没有任何交换，则说明已经排好了，不用进行下一轮
     */
    public void bubbleSort(int[] arr) {
        int count;
        for (int i = 0; i < arr.length; i++) {
            count = 0;
            for (int j = arr.length - 1; j > 0; j--) {
                int cur = arr[j];
                int pre = arr[j - 1];
                if (pre > cur) {
                    count++;
                    swap(arr, j, j - 1);
                }
            }
            if (count == 0) {
                return;
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
