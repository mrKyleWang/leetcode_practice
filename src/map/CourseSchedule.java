package map;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 207. 课程表
 * @author KyleWang
 * @version 1.0
 * @date 2020年08月04日
 */
public class CourseSchedule {


    /*
        你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
        在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
        给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
        示例 1:
            输入: 2, [[1,0]]
            输出: true
            解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
        示例 2:
            输入: 2, [[1,0],[0,1]]
            输出: false
            解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。

        提示：
            输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
            你可以假定输入的先决条件中没有重复的边。
            1 <= numCourses <= 10^5
     */

    @Test
    public void test() {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};
        Assert.assertEquals(true, canFinish(numCourses, prerequisites));
    }

    @Test
    public void test2() {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}, {0, 1}};
        Assert.assertEquals(false, canFinish(numCourses, prerequisites));
    }

    @Test
    public void test3() {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}, {0, 4}, {3, 2}, {2, 1}, {4, 3}};
        Assert.assertEquals(false, canFinish(numCourses, prerequisites));
    }

    @Test
    public void test4() {
        int numCourses = 3;
        int[][] prerequisites = {{0, 1}, {0, 2}, {1, 2}};
        Assert.assertEquals(true, canFinish(numCourses, prerequisites));
    }

    /**
     * 判断是否存在回环
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            List<Integer> list = map.computeIfAbsent(prerequisite[0], k -> new ArrayList<>());
            list.add(prerequisite[1]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (map.containsKey(i)) {
                HashSet<Integer> set = new HashSet<>();
                set.add(i);
                if (!check(set, map, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean check(Set<Integer> path, Map<Integer, List<Integer>> map, Integer cur) {
        List<Integer> prerequisites = map.get(cur);
        if (prerequisites != null) {
            for (Integer prerequisite : prerequisites) {
                if (map.containsKey(prerequisite)) {
                    if (path.contains(prerequisite)) {
                        return false;
                    }
                    path.add(prerequisite);
                    if (!check(path, map, prerequisite)) {
                        return false;
                    }
                    path.remove(prerequisite);
                }
            }
        }
        map.remove(cur);
        return true;
    }
}
