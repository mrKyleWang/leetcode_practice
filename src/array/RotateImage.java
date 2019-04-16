package array;

import java.util.Arrays;

/**
 * 旋转图像（48）
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月16日
 */
public class RotateImage {

	/*
	 * 给定一个 n × n 的二维矩阵表示一个图像。 将图像顺时针旋转 90 度。
	 *
	 * 说明： 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
	 *
	 * 给定 matrix = [
	 * [1,2,3],
	 * [4,5,6],
	 * [7,8,9] ],
	 *
	 * 原地旋转输入矩阵，使其变为: [
	 * [7,4,1],
	 * [8,5,2],
	 * [9,6,3] ]
	 */

	/*
	 * 思路：需要旋转的节点
	 * 1 1 1 1 1    0,1,2,3
	 * 1 1 1 1 1      1,2
	 * 1 1 1 1 1
	 * 1 1 1 1 1
	 * 1 1 1 1 1

	 * 1 1 1 1    0,1,2
	 * 1 1 1 1      1
	 * 1 1 1 1
	 * 1 1 1 1
	 */

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		rotate(matrix);
		for (int[] ints : matrix) {
			System.out.println(Arrays.toString(ints));
		}
	}

	public static void rotate(int[][] matrix) {
		int n = matrix.length;
		for (int row = 0; row < n; row++) {
			for (int column = row; column < n - row - 1; column++) {

				int currentRow = row;
				int currentColumn = column;
				int targetRow;
				int targetColumn;
				int current = matrix[row][column];
				int temp;
				for (int i = 0; i < 4; i++) {
					targetRow = currentColumn;
					targetColumn = n - currentRow - 1;
					temp = matrix[targetRow][targetColumn];
					matrix[targetRow][targetColumn] = current;
					currentRow = targetRow;
					currentColumn = targetColumn;
					current = temp;
				}
			}
		}
	}
}
