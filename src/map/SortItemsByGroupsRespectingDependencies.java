package map;

import org.junit.Test;

import java.util.*;

/**
 * 1203. 项目管理
 * @author KyleWang
 * @version 1.0
 * @date 2021年01月12日
 */
public class SortItemsByGroupsRespectingDependencies {

    /*
        公司共有 n 个项目和  m 个小组，每个项目要不无人接手，要不就由 m 个小组之一负责。
        group[i] 表示第 i 个项目所属的小组，如果这个项目目前无人接手，那么 group[i] 就等于 -1。（项目和小组都是从零开始编号的）小组可能存在没有接手任何项目的情况。
        请你帮忙按要求安排这些项目的进度，并返回排序后的项目列表：
        同一小组的项目，排序后在列表中彼此相邻。
        项目之间存在一定的依赖关系，我们用一个列表 beforeItems 来表示，其中 beforeItems[i] 表示在进行第 i 个项目前（位于第 i 个项目左侧）应该完成的所有项目。
        如果存在多个解决方案，只需要返回其中任意一个即可。如果没有合适的解决方案，就请返回一个 空列表 。

        示例 1：
            输入：n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3,6],[],[],[]]
            输出：[6,3,4,1,5,2,0,7]
        示例 2：
            输入：n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3],[],[4],[]]
            输出：[]
            解释：与示例 1 大致相同，但是在排序后的列表中，4 必须放在 6 的前面。
         
        提示：
            1 <= m <= n <= 3 * 104
            group.length == beforeItems.length == n
            -1 <= group[i] <= m - 1
            0 <= beforeItems[i].length <= n - 1
            0 <= beforeItems[i][j] <= n - 1
            i != beforeItems[i][j]
            beforeItems[i] 不含重复元素
     */

    @Test
    public void test() {
        int n = 8;
        int m = 2;
        int[] group = {-1, -1, 1, 0, 0, 1, 0, -1};
        List<List<Integer>> beforeItems = build(new int[][]{{}, {6}, {5}, {6}, {3, 6}, {}, {}, {}});
        System.out.println(Arrays.toString(sortItems(8, 2, group, beforeItems)));
    }


    @Test
    public void test2() {
        int[] group = {-1, -1, 1, 0, 0, 1, 0, -1};
        List<List<Integer>> beforeItems = build(new int[][]{{}, {6}, {5}, {6}, {3}, {}, {4}, {}});
        System.out.println(Arrays.toString(sortItems(8, 2, group, beforeItems)));
    }


    @Test
    public void test3() {
        int[] group = {2, 0, -1, 3, 0};
        List<List<Integer>> beforeItems = build(new int[][]{{2, 1, 3}, {2, 4}, {}, {}, {}});
        System.out.println(Arrays.toString(sortItems(5, 5, group, beforeItems)));
    }

    private List<List<Integer>> build(int[][] pairs) {
        List<List<Integer>> list = new ArrayList<>();
        for (int[] pair : pairs) {
            List<Integer> temp = new ArrayList<>();
            for (int i : pair) {
                temp.add(i);
            }
            list.add(temp);
        }
        return list;
    }

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        // 先给所有分配组为-1的，单独重新设置组
        for (int i = 0; i < group.length; i++) {
            if (group[i] == -1) {
                group[i] = m;
                m++;
            }
        }

        int[] groupInDeg = new int[m];
        int[] itemInDeg = new int[n];
        Map<Integer, List<Integer>> groupMap = new HashMap<>();
        Map<Integer, List<Integer>> itemMap = new HashMap<>();

        // 找到组之间的先后关系
        for (int i = 0; i < n; i++) {
            int curGroup = group[i];
            for (Integer beforeItem : beforeItems.get(i)) {
                int beforeGroup = group[beforeItem];
                if (curGroup != beforeGroup) {
                    groupMap.computeIfAbsent(beforeGroup, k -> new ArrayList<>()).add(curGroup);
                    groupInDeg[curGroup]++;
                }

                itemMap.computeIfAbsent(beforeItem, k -> new ArrayList<>()).add(i);
                itemInDeg[i]++;
            }
        }

        // 分别拓扑排序
        List<Integer> groupList = topSort(groupMap, groupInDeg, m);
        if (groupList.size() < m) {
            return new int[0];
        }
        List<Integer> itemList = topSort(itemMap, itemInDeg, n);
        if (itemList.size() < n) {
            return new int[0];
        }

        // 项目排序结果分组
        Map<Integer, List<Integer>> groupItemMap = new HashMap<>();
        for (Integer item : itemList) {
            groupItemMap.computeIfAbsent(group[item], k -> new ArrayList<>()).add(item);
        }

        // 按组排序结果拼接
        List<Integer> result = new ArrayList<>();
        for (Integer g : groupList) {
            if (groupItemMap.containsKey(g)) {
                result.addAll(groupItemMap.get(g));
            }
        }

        return result.stream().mapToInt(Integer::valueOf).toArray();
    }

    private List<Integer> topSort(Map<Integer, List<Integer>> map, int[] inDeg, int n) {
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDeg[i] == 0) {
                queue.offer(i);
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            result.add(poll);
            if (map.containsKey(poll)) {
                for (Integer before : map.get(poll)) {
                    if (--inDeg[before] == 0) {
                        queue.offer(before);
                    }
                }
            }
        }
        return result;
    }
}
