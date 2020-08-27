package dynamic;

import org.junit.Test;

import java.util.*;

/**
 * 332. 重新安排行程
 * @author KyleWang
 * @version 1.0
 * @date 2020年08月27日
 */
public class ReconstructItinerary {

    /*
        给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，
        对该行程进行重新规划排序。所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。

        说明:
            如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
            所有的机场都用三个大写字母表示（机场代码）。
            假定所有机票至少存在一种合理的行程。
        示例 1:
            输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
            输出: ["JFK", "MUC", "LHR", "SFO", "SJC"]
        示例 2:
            输入: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
            输出: ["JFK","ATL","JFK","SFO","ATL","SFO"]
            解释: 另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
     */

    @Test
    public void test() {
        List<List<String>> tickets = Arrays.asList(Arrays.asList("MUC", "LHR"), Arrays.asList("JFK", "MUC"), Arrays.asList("SFO", "SJC"), Arrays.asList("LHR", "SFO"));
        System.out.println(findItinerary(tickets));
    }

    @Test
    public void test2() {
        List<List<String>> tickets = Arrays.asList(Arrays.asList("JFK", "SFO"), Arrays.asList("JFK", "ATL"), Arrays.asList("SFO", "ATL"), Arrays.asList("ATL", "JFK"), Arrays.asList("ATL", "SFO"));
        System.out.println(findItinerary(tickets));
    }

    @Test
    public void test3() {
        List<List<String>> tickets = Arrays.asList(Arrays.asList("JFK", "KUL"), Arrays.asList("JFK", "NRT"), Arrays.asList("NRT", "JFK"));
        System.out.println(findItinerary(tickets));
    }

    @Test
    public void test4() {
        List<List<String>> tickets = Arrays.asList(Arrays.asList("EZE", "AXA"), Arrays.asList("TIA", "ANU"), Arrays.asList("ANU", "JFK"), Arrays.asList("JFK", "ANU"), Arrays.asList("ANU", "EZE"), Arrays.asList("TIA", "ANU"), Arrays.asList("AXA", "TIA"), Arrays.asList("TIA", "JFK"), Arrays.asList("ANU", "TIA"), Arrays.asList("JFK", "TIA"));
        System.out.println(findItinerary(tickets));
    }

    List<String> result;
    Map<String, List<String>> map;

    /**
     * 递归，深度优先，要考虑ticket会有重复的
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        result = new ArrayList<>();
        map = new HashMap<>(tickets.size());
        for (List<String> ticket : tickets) {
            String src = ticket.get(0);
            String target = ticket.get(1);
            List<String> list = map.computeIfAbsent(src, k -> new ArrayList<>());
            list.add(target);
            list.sort(null);
        }
        find("JFK", tickets.size());
        Collections.reverse(result);
        return result;
    }

    private boolean find(String cur, int count) {
        List<String> list = map.get(cur);
        if (count == 0) {
            result.add(cur);
            return true;
        }
        if (list == null || list.size() == 0) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            String next = list.get(i);
            list.remove(next);
            if (find(next, count - 1)) {
                result.add(cur);
                return true;
            } else {
                list.add(i, next);
            }
        }
        return false;
    }

}
