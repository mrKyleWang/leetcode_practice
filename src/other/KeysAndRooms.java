package other;

import org.junit.Test;

import java.util.*;

/**
 * 841. 钥匙和房间
 * @author KyleWang
 * @version 1.0
 * @date 2020/02/15
 */
public class KeysAndRooms {

    /*
        有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
        在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
        最初，除 0 号房间外的其余所有房间都被锁住。你可以自由地在房间之间来回走动。
        如果能进入每个房间返回 true，否则返回 false。

        示例 1：
            输入: [[1],[2],[3],[]]
            输出: true
            解释:
            我们从 0 号房间开始，拿到钥匙 1。
            之后我们去 1 号房间，拿到钥匙 2。
            然后我们去 2 号房间，拿到钥匙 3。
            最后我们去了 3 号房间。
            由于我们能够进入每个房间，我们返回 true。
        示例 2：
            输入：[[1,3],[3,0,1],[2],[0]]
            输出：false
            解释：我们不能进入 2 号房间。
        提示：
            1 <= rooms.length <= 1000
            0 <= rooms[i].length <= 1000
            所有房间中的钥匙数量总计不超过 3000。
     */

    @Test
    public void test() {
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(Arrays.asList(1,1));
        rooms.add(new ArrayList<>());
        System.out.println(canVisitAllRooms(rooms));

        List<List<Integer>> rooms2 = new ArrayList<>();
        rooms2.add(Arrays.asList(1, 3));
        rooms2.add(Arrays.asList(3, 0, 1));
        rooms2.add(Arrays.asList(2));
        rooms2.add(Arrays.asList(0));
        System.out.println(canVisitAllRooms(rooms2));
    }


    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> visitedRooms = new HashSet<>();
        // 从0号开始
        visitedRooms.add(0);
        List<Integer> keys = rooms.get(0);
        for (Integer key : keys) {
            if (visitRooms(rooms, key, visitedRooms)) {
                return true;
            }
        }
        return rooms.size() == 1;
    }

    /**
     * 层层递归，当visitedRooms记录去过的房间数等于所有房间数时则返回true
     */
    private boolean visitRooms(List<List<Integer>> rooms, int n, Set<Integer> visitedRooms) {
        // 记录足迹
        visitedRooms.add(n);
        if (visitedRooms.size() == rooms.size()) {
            return true;
        }

        // 进入其他房间
        List<Integer> keys = rooms.get(n);
        for (Integer key : keys) {
            if (!visitedRooms.contains(key) && visitRooms(rooms, key, visitedRooms)) {
                return true;
            }
        }
        return false;
    }
}
