package dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 120. 三角形最小路径和
 * @author KyleWang
 * @version 1.0
 * @date 2019/11/18
 */
public class MinimumTotal {

    /*
        给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
        例如，给定三角形：

        [
             [2],
            [3,4],
           [6,5,7],
          [4,1,8,3]
        ]
        自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
     */

    public static void main(String[] args) {
        ArrayList<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(2));
        list.add(Arrays.asList(3, 4));
        list.add(Arrays.asList(6, 5, 7));
        list.add(Arrays.asList(4, 1, 8, 3));
        System.out.println(new MinimumTotal().minimumTotal(list));
    }

    /**
     * 自下往上，遍历并保存每个节点从底部向上的路径最小值
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = triangle.get(i).size() - 1; j >= 0; j--) {
                // 下层相邻两数的值
                Integer current = triangle.get(i).get(j);
                Integer left = triangle.get(i + 1).get(j);
                Integer right = triangle.get(i + 1).get(j + 1);
                Integer min = Math.min(left, right) + current;
                triangle.get(i).set(j, min);
            }
        }
        return triangle.get(0).get(0);
    }
}
