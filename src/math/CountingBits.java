package math;

import java.util.Arrays;

/**
 * 338. 比特位计数
 * @author KyleWang
 * @version 1.0
 * @date 2019/11/11
 */
public class CountingBits {

    /*
        给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。

        示例 1:
        输入: 2
        输出: [0,1,1]

        示例 2:
        输入: 5
        输出: [0,1,1,2,1,2]

        进阶:
        给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
        要求算法的空间复杂度为O(n)。
        你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
    */

    public static void main(String[] args) {
        System.out.println(1 & (0));

        System.out.println(Arrays.toString(new CountingBits().countBits(5)));
    }

    /**
     * 位运算：x&(x-1)相当于消除最后一位1，count(x) = count(x&(x-1)) + 1，且x&(x-1)比x小，一定在前面计算过
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        result[0] = 0;
        for (int i = 1; i <= num; i++) {
            int count = result[i & (i - 1)] + 1;
            result[i] = count;
        }
        return result;
    }
}
