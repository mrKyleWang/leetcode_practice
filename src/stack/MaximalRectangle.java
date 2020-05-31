package stack;

import org.junit.Test;

import java.util.Stack;

/**
 * 85. 最大矩形
 * @author KyleWang
 * @version 1.0
 * @date 2020年05月31日
 */
public class MaximalRectangle {

    /*
        给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。

        示例:
        输入:
            [
              ["1","0","1","0","0"],
              ["1","0","1","1","1"],
              ["1","1","1","1","1"],
              ["1","0","0","1","0"]
            ]
        输出: 6



            3 1 3 2 2
     */

    @Test
    public void test() {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};
        System.out.println(maximalRectangle(matrix));
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix.length <= 0 || matrix[0].length <= 0) {
            return 0;
        }
        int max = 0;
        // 保存前一行第n列向上最大连续为1的高度
        int[] rowHeight = new int[matrix[0].length];
        // 扫描每行
        for (int m = 0; m < matrix.length; m++) {
            // 遍历每列，通过单调栈，找到以当前行为底的最大矩形面积
            Stack<Integer> stack = new Stack<>();
            for (int n = 0; n < matrix[m].length; n++) {
                // 通过上一层的高度计算当前高度
                if (matrix[m][n] == '1') {
                    int preHeight = rowHeight[n];
                    if (preHeight > 0) {
                        rowHeight[n] = preHeight + 1;
                    } else {
                        rowHeight[n] = 1;
                    }
                } else {
                    rowHeight[n] = 0;
                }

                // 计算面积
                while (!stack.isEmpty() && rowHeight[stack.peek()] > rowHeight[n]) {
                    Integer index = stack.pop();
                    int leftLimit = -1;
                    if (!stack.isEmpty()) {
                        leftLimit = stack.peek();
                    }
                    int area = (n - leftLimit - 1) * rowHeight[index];
                    max = Math.max(max, area);
                }
                stack.push(n);
            }

            // 处理stack剩余
            while (!stack.isEmpty()) {
                Integer index = stack.pop();
                int leftLimit = -1;
                if (!stack.isEmpty()) {
                    leftLimit = stack.peek();
                }
                int area = (matrix[m].length - leftLimit - 1) * rowHeight[index];
                max = Math.max(max, area);
            }
        }
        return max;
    }


}
