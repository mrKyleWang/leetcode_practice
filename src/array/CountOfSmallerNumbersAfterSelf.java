package array;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 315. 计算右侧小于当前元素的个数
 * @author KyleWang
 * @version 1.0
 * @date 2020年07月11日
 */
public class CountOfSmallerNumbersAfterSelf {

    /*
    给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
    示例:
        输入: [5,2,6,1]
        输出: [2,1,1,0]
        解释:
            5 的右侧有 2 个更小的元素 (2 和 1).
            2 的右侧仅有 1 个更小的元素 (1).
            6 的右侧有 1 个更小的元素 (1).
            1 的右侧有 0 个更小的元素.

     */

    @Test
    public void test() {
        List<Integer> countSmaller = countSmaller(new int[]{1, 1});
        System.out.println(Arrays.toString(countSmaller.toArray()));
    }

    @Test
    public void test2() {
        List<Integer> countSmaller = countSmaller(new int[]{5, 2, 6, 1});
        System.out.println(Arrays.toString(countSmaller.toArray()));
    }

    @Test
    public void test3() {
        List<Integer> countSmaller = countSmaller(new int[]{26, 78, 27, 100, 33, 67, 90, 23, 66, 5, 38, 7, 35, 23, 52, 22, 83, 51, 98, 69, 81, 32, 78, 28, 94, 13, 2, 97, 3, 76, 99, 51, 9, 21, 84, 66, 65, 36, 100, 41});
        System.out.println(Arrays.toString(countSmaller.toArray()));
    }

    /**
     *
     */
    public List<Integer> countSmaller(int[] nums) {
        int[] result = new int[nums.length];
        if (nums.length > 0) {
            result[nums.length - 1] = 0;
            SearchTreeNode root = new SearchTreeNode(nums[nums.length - 1]);
            for (int i = nums.length - 2; i >= 0; i--) {
                result[i] = SearchTreeNode.insert(root, nums[i]);
            }
            System.out.println();
        }
        return Arrays.stream(result).boxed().collect(Collectors.toList());
    }

    /**
     * 搜索二叉树，并保存左子节点的数量，如果新节点node 在cur节点右侧，则可知至少cur.leftCount + 1 个元素小于node
     */
    static class SearchTreeNode {
        int val;
        int leftCount;
        SearchTreeNode left;
        SearchTreeNode right;

        public SearchTreeNode(int val) {
            this.val = val;
        }


        public static int insert(SearchTreeNode root, int num) {
            int count = 0;
            SearchTreeNode cur = root;
            while (true) {
                if (num <= cur.val) {
                    cur.leftCount += 1;
                    if (cur.left == null) {
                        cur.left = new SearchTreeNode(num);
                        break;
                    }
                    cur = cur.left;
                } else {
                    count += (cur.leftCount + 1);
                    if (cur.right == null) {
                        cur.right = new SearchTreeNode(num);
                        break;
                    }
                    cur = cur.right;
                }
            }
            return count;
        }
    }
}
