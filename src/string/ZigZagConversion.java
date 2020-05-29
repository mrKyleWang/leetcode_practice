package string;

import org.junit.Test;

/**
 * 6. Z 字形变换
 * @author KyleWang
 * @version 1.0
 * @date 2020年05月29日
 */
public class ZigZagConversion {

    /*
        将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

        比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：

            L   C   I   R
            E T O E S I I G
            E   D   H   N
        之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。

        示例 1:
            输入: s = "LEETCODEISHIRING", numRows = 4
            输出: "LDREOEIIECIHNTSG"
            解释:
                L     D     R
                E   O E   I I
                E C   I H   N
                T     S     G

     */

    @Test
    public void test() {
        System.out.println(convert("AB", 3));
    }

    private int[] UP = {-1, 1};
    private int[] DOWN = {1, 0};

    public String convert(String s, int numRows) {
        int size = s.length();
        if (size == 0 || numRows == 1){
            return s;
        }
        char[] result = new char[size];
        int blockSize = 2 * numRows - 2;
        int colSize = size % blockSize == 0 ? (size / blockSize) * (numRows - 1) : (size / blockSize + 1) * (numRows - 1);
        char[][] matrix = new char[numRows][colSize];
        char[] chars = s.toCharArray();

        int row = 0;
        int col = 0;
        int[] direction = DOWN;
        for (int i = 0; i < size; i++) {
            matrix[row][col] = chars[i];
            if (row + direction[0] >= 0 && row + direction[0] < numRows) {
                row += direction[0];
                col += direction[1];
            } else {
                direction = direction == UP ? DOWN : UP;
                row += direction[0];
                col += direction[1];
            }
        }
        int index = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < colSize; j++) {
                char c = matrix[i][j];
                if (c!=0){
                    result[index] = c;
                    index ++;
                }
            }
        }
        return new String(result);
    }
}
