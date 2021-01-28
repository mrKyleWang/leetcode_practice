package sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 快速排序
 * @author KyleWang
 * @version 1.0
 * @date 2021年01月28日
 */
public class QuickSort {


    @Test
    public void test() {
        int[] nums = {63, 57, 70, 50, 25, 83, 41, 22, 59, 47, 4, 20, 55, 27, 36, 64, 58, 74, 96, 38, 67, 86, 72, 97, 6, 15, 35, 93, 34, 24, 53, 88, 32, 69, 56, 84, 44, 71, 49, 14, 9, 82, 31, 79, 68, 13, 3, 65, 8, 43, 23, 66, 98, 77, 26, 90, 51, 39, 60, 17, 85, 46, 0, 10, 73, 92, 33, 42, 21, 19, 12, 89, 87, 18, 91, 62, 1, 2, 40, 80, 45, 7, 37, 48, 11, 81, 5, 30, 95, 75, 54, 94, 29, 78, 52, 76, 16, 99, 28, 61};
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }


    public void quickSort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    private void sort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int p = arr[start];
        int l = start;
        int r = end;
        while (l < r) {
            while (l < r && arr[r] >= p) {
                r--;
            }
            while (l < r && arr[l] <= p) {
                l++;
            }
            swap(arr, l, r);
        }
        swap(arr, l, start);
        sort(arr, start, l - 1);
        sort(arr, l + 1, end);
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
