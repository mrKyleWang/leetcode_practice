package array;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 354. 俄罗斯套娃信封问题
 * @author KyleWang
 * @version 1.0
 * @date 2021年03月04日
 */
public class RussianDollEnvelopes {

    /*
        给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。

        请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。

        说明:
        不允许旋转信封。

        示例:

        输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
        输出: 3
        解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
     */

    @Test
    public void test() {
        int[][] envelopes = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        Assert.assertEquals(3, maxEnvelopes(envelopes));
    }

    /**
     * 基于【最长子序列】的思路，将envelopes按某一维度先排序
     * 这里当w相同时按h降序，是为了保证同一w下，只有一个h值加入list，因为宽高相等是不能放入的
     */
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0){
            return 0;
        }
        Arrays.sort(envelopes, (e1, e2) -> e1[0] != e2[0] ? e1[0] - e2[0] : e2[1] - e1[1]);
        List<Integer> list = new ArrayList<>();
        list.add(envelopes[0][1]);
        for (int i = 1; i < envelopes.length; i++) {
            int h = envelopes[i][1];
            if (h > list.get(list.size() - 1)) {
                list.add(h);
            } else {
                list.set(search(list, h), h);
            }
        }
        return list.size();
    }

    private int search(List<Integer> list, int num) {
        int l = 0, r = list.size() - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (list.get(mid) < num) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
