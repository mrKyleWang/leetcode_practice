package queue;

import java.util.PriorityQueue;

/**
 * 703. 数据流中的第K大元素
 * @author KyleWang
 * @version 1.0
 * @date 2019年09月09日
 */
public class KthLargest {

    /*
        使用优先队列（堆实现）来保存前k大元素，每次新增元素对比堆顶元素，如果小于新增元素则移除。第k大元素即为堆顶元素
     */

    private PriorityQueue<Integer> queue;

    private int capacity;

    public KthLargest(int k, int[] nums) {
        capacity = k;
        queue = new PriorityQueue<>(k);
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (queue.size() < capacity) {
            queue.offer(val);
        } else if (queue.peek() < val) {
            queue.poll();
            queue.offer(val);
        }
        return queue.peek();
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        KthLargest kthLargest = new KthLargest(5, nums);
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.add(11));
        System.out.println(kthLargest.add(1));
    }
}
