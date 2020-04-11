package array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. 杨辉三角 II
 * @author KyleWang
 * @version 1.0
 * @date 2020/03/07
 */
public class PascalTriangleII {

    /*
        给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
        例：
            输入: 3
            输出: [1,3,3,1]

                 [1],
                [1,1],
               [1,2,1],
              [1,3,3,1],
             [1,4,6,4,1],
           [1,5,10,10,5,1]
          [1,6,15,20,15,6,1]    6
          1,7,21,35,35,21,7,1

     */

    /*
            (n)!
           -----
           (n-m)! (m)!

           (n-2)!
           ------
           (n-m+1)!(m-1)!

           n-m+1
           -----
           m

     */

    @Test
    public void test() {
        List<Integer> result = getRow(30);
        System.out.println(result);
    }

    /**
     * 使用组合数公式
     * 杨辉三角性质之一：第n行的m个数可表示为 C(n-1，m-1)，即为从n-1个不同元素中取m-1个元素的组合数
     * 组合数公式：C(n,m)=n!/((n-m)!*m!)  （m≤n）
     * 得出n行的每一项与前一项的关系：c(n,m) = c(n,m-1) * (n-m+1)/m
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            if (i <= rowIndex / 2) {
                // 计算结果会超出int范围，先用long存中间值
                long num = (long) list.get(i - 1) * (rowIndex - i + 1) / i;
                list.add((int) num);
            } else {
                list.add(list.get(rowIndex - i));
            }
        }
        return list;
    }
}
