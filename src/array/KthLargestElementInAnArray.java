package array;

import org.junit.Assert;
import org.junit.Test;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * 215. 数组中的第K个最大元素
 * @author KyleWang
 * @version 1.0
 * @date 2020年06月29日
 */
public class KthLargestElementInAnArray {

    /*
        在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

        示例 1:

        输入: [3,2,1,5,6,4] 和 k = 2
        输出: 5
        示例 2:

        输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
        输出: 4
        说明:

        你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
     */

    @Test
    public void test() {
        int[] arr = {3, 2, 1, 5, 6, 4};
        int kthLargest = findKthLargest3(arr, 2);
        Assert.assertEquals(5, kthLargest);
    }

    @Test
    public void test2() {
        int[] arr = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int kthLargest = findKthLargest3(arr, 4);
        Assert.assertEquals(4, kthLargest);
    }

    /**
     * 方法1：排序，O(nlogn)
     */
    public int findKthLargest(int[] nums, int k) {
        quickSort(nums, 0, nums.length - 1);
        return nums[nums.length - k];
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start < end) {
            int pivot = nums[start];
            int l = start, r = end;
            while (l < r) {
                while (l < r && nums[r] >= pivot) {
                    r--;
                }
                while (l < r && nums[l] <= pivot) {
                    l++;
                }
                swap(nums, l, r);
            }
            swap(nums, start, l);
            quickSort(nums, start, l - 1);
            quickSort(nums, l + 1, end);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 方法2：使用堆，O(nlogk)
     */
    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (int num : nums) {
            if (queue.size() >= k && queue.peek() < num) {
                queue.remove();
                queue.add(num);
            } else if (queue.size() < k) {
                queue.add(num);
            }
        }
        return queue.peek();
    }

    /**
     * 方法3：使用快排思想，将数组二分，pivot所在位置即是其在数组中排序后的最终位置
     */
    public int findKthLargest3(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    Random random = new Random();

    /**
     * 这里在选取pivot时，使用了随机位置，lc上性能更好
     */
    private int quickSelect(int[] nums, int start, int end, int k) {
        if (start == end) {
            return nums[start];
        }
        swap(nums, start, start + random.nextInt(end - start + 1));
        int pivot = nums[start];
        int l = start, r = end;
        while (l < r) {
            while (l < r && nums[r] >= pivot) {
                r--;
            }
            while (l < r && nums[l] <= pivot) {
                l++;
            }
            swap(nums, l, r);
        }
        swap(nums, start, l);
        if (l == k) {
            return pivot;
        } else if (l < k) {
            return quickSelect(nums, l + 1, end, k);
        } else {
            return quickSelect(nums, start, l - 1, k);
        }

    }
}
