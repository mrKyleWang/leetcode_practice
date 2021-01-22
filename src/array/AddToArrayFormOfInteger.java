package array;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * 989. 数组形式的整数加法
 * @author KyleWang
 * @version 1.0
 * @date 2021年01月22日
 */
public class AddToArrayFormOfInteger {

    /*
        对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
        给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。

        示例 1：
            输入：A = [1,2,0,0], K = 34
            输出：[1,2,3,4]
            解释：1200 + 34 = 1234
        示例 2：
            输入：A = [2,7,4], K = 181
            输出：[4,5,5]
            解释：274 + 181 = 455
        示例 3：
            输入：A = [2,1,5], K = 806
            输出：[1,0,2,1]
            解释：215 + 806 = 1021
        示例 4：
            输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
            输出：[1,0,0,0,0,0,0,0,0,0,0]
            解释：9999999999 + 1 = 10000000000

        提示：
            1 <= A.length <= 10000
            0 <= A[i] <= 9
            0 <= K <= 10000
            如果 A.length > 1，那么 A[0] != 0
     */

    @Test
    public void test() {
        int[] A = {1, 2, 0, 0};
        int K = 34;
        System.out.println(addToArrayForm(A, K));
    }

    @Test
    public void test2() {
        int[] A = {2, 7, 4};
        int K = 181;
        System.out.println(addToArrayForm(A, K));
    }

    public List<Integer> addToArrayForm(int[] A, int K) {
        LinkedList<Integer> list = new LinkedList<>();
        int add = 0;
        int i = A.length - 1;
        while (i >= 0 || K > 0) {
            int num = add;
            if (i >= 0) {
                num += A[i];
            }
            num += K % 10;
            K /= 10;
            if (num >= 10) {
                num -= 10;
                add = 1;
            } else {
                add = 0;
            }
            list.addFirst(num);
            i--;
        }
        if (add > 0) {
            list.addFirst(1);
        }
        return list;
    }
}
