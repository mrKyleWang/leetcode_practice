package queue;

import org.junit.Test;

import java.util.Arrays;

/**
 * 733. 图像渲染
 * @author KyleWang
 * @version 1.0
 * @date 2020/02/03
 */
public class FloodFill {

    /*
        有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
        给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。
        为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，
        接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。
        将所有有记录的像素点的颜色值改为新的颜色值。最后返回经过上色渲染后的图像。

        示例 1:
        输入:
            image = [[1,1,1],[1,1,0],[1,0,1]]
            sr = 1, sc = 1, newColor = 2
        输出: [[2,2,2],[2,2,0],[2,0,1]]
        解析:
            在图像的正中间，(坐标(sr,sc)=(1,1)),
            在路径上所有符合条件的像素点的颜色都被更改成2。
            注意，右下角的像素没有更改为2，
            因为它不是在上下左右四个方向上与初始点相连的像素点。
    */


    @Test
    public void test() {
//        int[][] image = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        int[][] image = {{0,0,0}, {0,1,0}};
        System.out.println(Arrays.deepToString(floodFill(image, 1, 1, 2)));
    }

    private int[][] direction = {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int[][] filled = new int[image.length][image[0].length];
        floodFill(image, sr, sc, image[sr][sc], newColor, filled);
        return image;
    }

    /**
     * 深度优先算法，使用递归向四个方向遍历
     */
    private void floodFill(int[][] image, int r, int c, int color, int newColor, int[][] filled) {
        for (int[] ints : direction) {
            int newR = r + ints[0];
            int newC = c + ints[1];
            if (newR >= 0 && newR < image.length && newC >= 0 && newC < image[0].length && filled[newR][newC] == 0 && image[newR][newC] == color) {
                image[newR][newC] = newColor;
                filled[newR][newC] = 1;
                floodFill(image, newR, newC, color, newColor, filled);
            }
        }
    }
}
