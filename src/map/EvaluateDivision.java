package map;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 399. 除法求值
 * @author KyleWang
 * @version 1.0
 * @date 2021年01月06日
 */
public class EvaluateDivision {

    /*
        给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，
        其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。
        每个 Ai 或 Bi 是一个表示单个变量的字符串。
        另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
        返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。

        注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
        示例 1：
            输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
            输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
            解释：
            条件：a / b = 2.0, b / c = 3.0
            问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
            结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
        示例 2：
            输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
            输出：[3.75000,0.40000,5.00000,0.20000]
        示例 3：
            输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
            输出：[0.50000,2.00000,-1.00000,-1.00000]

        提示：
            1 <= equations.length <= 20
            equations[i].length == 2
            1 <= Ai.length, Bi.length <= 5
            values.length == equations.length
            0.0 < values[i] <= 20.0
            1 <= queries.length <= 20
            queries[i].length == 2
            1 <= Cj.length, Dj.length <= 5
            Ai, Bi, Cj, Dj 由小写英文字母与数字组成

     */

    private List<List<String>> convert(String[]... strings) {
        return Arrays.stream(strings).map(Arrays::asList).collect(Collectors.toList());
    }

    @Test
    public void test() {
        String[][] equations = {{"a", "b"}, {"b", "c"}};
        double[] values = {2.0, 3.0};
        String[][] queries = {{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
        System.out.println(Arrays.toString(calcEquation(convert(equations), values, convert(queries))));
    }

    @Test
    public void test2() {
        String[][] equations = {{"a", "b"}, {"b", "c"}, {"bc", "cd"}};

        double[] values = {1.5, 2.5, 5.0};
        String[][] queries = {{"a", "c"}, {"c", "b"}, {"bc", "cd"}, {"cd", "bc"}};
        System.out.println(Arrays.toString(calcEquation(convert(equations), values, convert(queries))));
    }

    @Test
    public void test3() {
        String[][] equations = {{"a", "b"}};
        double[] values = {0.5};
        String[][] queries = {{"a", "b"}, {"b", "a"}, {"a", "c"}, {"x", "y"}};
        System.out.println(Arrays.toString(calcEquation(convert(equations), values, convert(queries))));
    }

    @Test
    public void test4() {
        String[][] equations = {{"a", "b"}, {"c", "d"}, {"e", "f"}, {"g", "h"}};
        double[] values = {4.5, 2.3, 8.9, 0.44};
        String[][] queries = {{"a", "c"}, {"d", "f"}, {"h", "e"}, {"b", "e"}, {"d", "h"}, {"g", "f"}, {"c", "g"}};
        System.out.println(Arrays.toString(calcEquation(convert(equations), values, convert(queries))));
    }

    /**
     * 先用map保存所有变量的相除结果
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] result = new double[queries.size()];
        Map<String, Map<String, Double>> map = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            List<String> variable = equations.get(i);
            String dividend = variable.get(0);
            String divisor = variable.get(1);
            double value = values[i];
            map.computeIfAbsent(dividend, k -> new HashMap<>()).put(divisor, value);
            map.computeIfAbsent(divisor, k -> new HashMap<>()).put(dividend, 1 / value);
        }
        for (int i = 0; i < result.length; i++) {
            List<String> query = queries.get(i);
            String dividend = query.get(0);
            String divisor = query.get(1);
            HashSet<String> path = new HashSet<>();
            path.add(dividend);
            result[i] = query(map, dividend, divisor, path, 1);
        }
        return result;
    }

    private double query(Map<String, Map<String, Double>> map, String dividend, String divisor, Set<String> path, double cur) {
        double value = -1;
        if (map.containsKey(dividend)) {
            for (Map.Entry<String, Double> valueEntry : map.get(dividend).entrySet()) {
                if (valueEntry.getKey().equals(divisor)) {
                    value = valueEntry.getValue();
                } else if (!path.contains(valueEntry.getKey()) && valueEntry.getValue() > 0) {
                    path.add(valueEntry.getKey());
                    value = query(map, valueEntry.getKey(), divisor, path, valueEntry.getValue());
                }
                if (value > 0) {
                    break;
                }
            }
        }
        map.computeIfAbsent(dividend, k -> new HashMap<>()).put(divisor, value);
        if (value > 0) {
            value *= cur;
        }
        return value;
    }
}
