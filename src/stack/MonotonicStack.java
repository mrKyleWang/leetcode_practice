package stack;

import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * 单调栈结构
 * @author KyleWang
 * @version 1.0
 * @date 2020年05月18日
 */
public class MonotonicStack {

    /*
        给定一个不含有重复值的数组arr，找到每一个i位置左边和右边离i最近且值比arr[i]小的位置，返回所有位置相应的信息
        例：
            arr = {3,4,1,5,6,2,7}
            result = {
                        {-1,2},
                        {0,2},
                        {-1,-1},
                        {2,5},
                        {3,5},
                        {2,-1},
                        {5,-1},
                      }
     */

    @Test
    public void test() {
        int[] arr = {3, 4, 1, 5, 6, 2, 7};
        System.out.println(Arrays.deepToString(findNearestSmaller(arr)));
    }

    /**
     * 使用一个栈，保存index，保证从底到顶arr[index]递增
     * （也就是说stack中从小到大也是按遍历时间顺序排列的）
     * 遍历到i时，如果arr[stack.peek()]的值比arr[i]小，则直接将i push进去，并且对于i来说，这个stack.peek()就是左边小于它的最近索引，
     * 如果比arr[i]小，则需要pop出来，且对于pop出来的这些index，此时i就是右边小于它的最近索引。
     * 对于最后stack中剩余的，说明对于这些index，右边没有更小的数
     */
    public int[][] findNearestSmaller(int[] arr) {
        int[][] result = new int[arr.length][2];

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                Integer index = stack.pop();
                result[index][1] = i;
            }
            if (stack.isEmpty()) {
                result[i][0] = -1;
            } else {
                result[i][0] = stack.peek();
            }
            stack.push(i);
        }

        // 处理stack剩余的
        while (!stack.isEmpty()){
            Integer index = stack.pop();
            result[index][1] = -1;
        }
        return result;
    }
}
