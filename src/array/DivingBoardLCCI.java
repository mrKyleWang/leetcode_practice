package array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 面试题 16.11. 跳水板
 * @author KyleWang
 * @version 1.0
 * @date 2020年07月08日
 */
public class DivingBoardLCCI {

    /*
        你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
        返回的长度需要从小到大排列。
        示例：
            输入：
            shorter = 1
            longer = 2
            k = 3
            输出： {3,4,5,6}
        提示：
            0 < shorter <= longer
            0 <= k <= 100000

     */

    @Test
    public void test() {
        int[] board = divingBoard(1, 2, 3);
        System.out.println(Arrays.toString(board));
    }

    public int[] divingBoard(int shorter, int longer, int k) {
        if (k > 0) {
            int[] result = new int[k + 1];
            if (shorter == longer) {
                return new int[]{shorter * k};
            }
            for (int i = 0; i <= k; i++) {
                int len = i * longer + (k - i) * shorter;
                result[i] = len;
            }
            return result;
        }
        return new int[0];
    }
}
