package queue;

import org.junit.Assert;
import org.junit.Test;

import java.util.PriorityQueue;

/**
 * 5673. 移除石子的最大得分
 * @author KyleWang
 * @version 1.0
 * @date 2021年02月07日
 */
public class MaximumScoreFromRemovingStones {
    /*
        你正在玩一个单人游戏，面前放置着大小分别为 a​​​​​​、b 和 c​​​​​​ 的 三堆 石子。
        每回合你都要从两个 不同的非空堆 中取出一颗石子，并在得分上加 1 分。当存在 两个或更多 的空堆时，游戏停止。
        给你三个整数 a 、b 和 c ，返回可以得到的 最大分数 。
         
        示例 1：
            输入：a = 2, b = 4, c = 6
            输出：6
            解释：石子起始状态是 (2, 4, 6) ，最优的一组操作是：
            - 从第一和第三堆取，石子状态现在是 (1, 4, 5)
            - 从第一和第三堆取，石子状态现在是 (0, 4, 4)
            - 从第二和第三堆取，石子状态现在是 (0, 3, 3)
            - 从第二和第三堆取，石子状态现在是 (0, 2, 2)
            - 从第二和第三堆取，石子状态现在是 (0, 1, 1)
            - 从第二和第三堆取，石子状态现在是 (0, 0, 0)
            总分：6 分 。
        示例 2：
            输入：a = 4, b = 4, c = 6
            输出：7
            解释：石子起始状态是 (4, 4, 6) ，最优的一组操作是：
            - 从第一和第二堆取，石子状态现在是 (3, 3, 6)
            - 从第一和第三堆取，石子状态现在是 (2, 3, 5)
            - 从第一和第三堆取，石子状态现在是 (1, 3, 4)
            - 从第一和第三堆取，石子状态现在是 (0, 3, 3)
            - 从第二和第三堆取，石子状态现在是 (0, 2, 2)
            - 从第二和第三堆取，石子状态现在是 (0, 1, 1)
            - 从第二和第三堆取，石子状态现在是 (0, 0, 0)
            总分：7 分 。
        示例 3：
            输入：a = 1, b = 8, c = 8
            输出：8
            解释：最优的一组操作是连续从第二和第三堆取 8 回合，直到将它们取空。
            注意，由于第二和第三堆已经空了，游戏结束，不能继续从第一堆中取石子。
         

        提示：
            1 <= a, b, c <= 105
     */
    @Test
    public void test() {
        Assert.assertEquals(3, maximumScore(6, 2, 1));
    }

    public int maximumScore(int a, int b, int c) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(3, (o1, o2) -> o2 - o1);
        queue.offer(a);
        queue.offer(b);
        queue.offer(c);
        int count = 0;
        int res = 0;
        while (count < 2 && queue.size() > 1) {
            int j = queue.poll();
            int k = queue.poll();
            if (--j == 0) {
                count++;
            } else {
                queue.offer(j);
            }
            if (--k == 0) {
                count++;
            } else {
                queue.offer(k);
            }
            res++;
        }
        return res;
    }
}
