package array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 加一（66）
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月14日
 */
public class PlusOne {

    /*
	 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
	 * 
	 * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
	 * 
	 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
	 */

    public static void main(String[] args) {
        int[] nums = {9};
        System.out.println(Arrays.toString(plusOne(nums)));
    }

    public static int[] plusOne(int[] digits) {
        ArrayList<Integer> list = new ArrayList<>();
        boolean full = false;
        int index = digits.length - 1;
        do {
            int num = 0;
            if (index == digits.length - 1) {
                num++;
            }
            if (full) {
                num++;
                full = false;
            }
            if (index >= 0) {
                num += digits[index];
            }
            if (num >= 10) {
                num -= 10;
                full = true;
            }
            list.add(num);
            index--;
        } while (index >= 0 || full);
        int[] result = new int[list.size()];
        int index2 = result.length - 1;
        for (Integer integer : list) {
            result[index2] = integer;
            index2--;
        }
        return result;
    }
}
