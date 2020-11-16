package array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 406. 根据身高重建队列
 * @author KyleWang
 * @version 1.0
 * @date 2020年11月16日
 */
public class QueueReconstructionByHeight {


    /*
        假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
        注意：
        总人数少于1100人。

        示例
            输入:
            [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

            输出:
            [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
     */

    @Test
    public void test() {
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        System.out.println(Arrays.deepToString(reconstructQueue(people)));
    }

    /**
     * 先对输入数组排序，h升序，k降序 从头循环遍历 当前这个人就是剩下未安排的人中最矮的人，他的k值就代表他在剩余空位的索引值
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> {
            int diff = o1[0] - o2[0];
            if (diff == 0) {
                return o2[1] - o1[1];
            }
            return o1[0] - o2[0];
        });

        int[][] result = new int[people.length][2];
        boolean[] temp = new boolean[people.length];
        for (int[] person : people) {
            int k = person[1];
            for (int i = 0; i < people.length; i++) {
                if (!temp[i]) {
                    k--;
                }
                if (k == -1) {
                    result[i] = person;
                    temp[i] = true;
                    break;
                }
            }
        }

        return result;
    }
}
