package array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. 杨辉三角
 * @author KyleWang
 * @version 1.0
 * @date 2020/03/07
 */
public class PascalTriangle {

    /*
        给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
        在杨辉三角中，每个数是它左上方和右上方的数的和。
        例：
            输入: 5
            输出:
            [
                 [1],
                [1,1],
               [1,2,1],
              [1,3,3,1],
             [1,4,6,4,1]
            ]
     */

    @Test
    public void test() {
        List<List<Integer>> result = generate(0);
        for (List<Integer> integers : result) {
            System.out.println(integers);
        }

    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            int mid = i / 2;
            for (int j = 1; j <= mid; j++) {
                List<Integer> preRow = result.get(i - 1);
                list.add(preRow.get(j - 1) + preRow.get(j));
            }
            while (list.size() < i + 1) {
                list.add(list.get(i - list.size()));
            }
            result.add(list);
        }
        return result;
    }
}
