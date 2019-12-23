package stack;

import org.junit.Test;

import java.util.Arrays;

/**
 * 739. 每日温度
 * @author KyleWang
 * @version 1.0
 * @date 2019/12/23
 */
public class DailyTemperatures {


    /*
        根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。

        例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

        提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
     */

    @Test
    public void test() {
        int[] temperatures = {55, 38, 53, 81, 61, 93, 97, 32, 43, 78};
        System.out.println(Arrays.toString(dailyTemperatures(temperatures)));
    }

    /**
     * 从后向前推，如果比前一个数大，则前一个数的结果为1，否则根据当前索引的结果向后推，直到找到比pre大的结果
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        for (int i = T.length - 1; i > 0; i--) {
            int cur = T[i];
            int pre = T[i - 1];
            int key = i;
            while (cur <= pre && result[key] != 0) {
                key = key + result[key];
                cur = T[key];
            }
            if (pre < cur) {
                result[i - 1] = key - (i - 1);
            }
        }
        return result;
    }
}
