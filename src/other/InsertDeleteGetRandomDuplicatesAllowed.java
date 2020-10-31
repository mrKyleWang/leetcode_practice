package other;

import org.junit.Test;

import java.util.*;

/**
 * 381. O(1) 时间插入、删除和获取随机元素 - 允许重复
 * @author KyleWang
 * @version 1.0
 * @date 2020年10月31日
 */
public class InsertDeleteGetRandomDuplicatesAllowed {

    /*
        设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。

        注意: 允许出现重复元素。

        insert(val)：向集合中插入元素 val。
        remove(val)：当 val 存在时，从集合中移除一个 val。
        getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
     */

    @Test
    public void test() {
        // 初始化一个空的集合。
        RandomizedCollection collection = new RandomizedCollection();
        // 向集合中插入 1 。返回 true 表示集合不包含 1 。
        System.out.println(collection.insert(1));
        // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
//        System.out.println(collection.insert(1));
        // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
        System.out.println(collection.insert(2));
        // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
        collection.getRandom();
        // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
        System.out.println(collection.remove(1));
        System.out.println(collection.remove(1));
        // getRandom 应有相同概率返回 1 和 2 。
        collection.getRandom();
    }


    /**
     * 关键在于删除元素，如果使得ArrayList删除元素也能达到O1
     * 方法是将要删除的元素与tail位置的元素交换位置，并更新map中原tail的值的索引set
     */
    class RandomizedCollection {

        /**
         * 保存所有数值
         */
        List<Integer> data;
        /**
         * key:num, set:多个值在data中的索引
         */
        Map<Integer, Set<Integer>> map;

        /**
         * Initialize your data structure here.
         */
        public RandomizedCollection() {
            data = new ArrayList<>();
            map = new HashMap<>();
        }

        /**
         * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
         */
        public boolean insert(int val) {
            boolean flag = true;
            int index = data.size();
            data.add(val);
            if (map.containsKey(val)) {
                map.get(val).add(index);
                flag = false;
            } else {
                Set<Integer> set = new HashSet<>();
                set.add(index);
                map.put(val, set);
            }
            return flag;
        }

        /**
         * Removes a value from the collection. Returns true if the collection contained the specified element.
         */
        public boolean remove(int val) {
            if (map.containsKey(val)) {
                Set<Integer> set = map.get(val);
                if (set.size() > 0) {
                    Integer index = set.iterator().next();
                    set.remove(index);
                    int tailIndex = data.size() - 1;
                    if (index != tailIndex) {
                        Integer tailVal = data.get(tailIndex);
                        Set<Integer> tailSet = map.get(tailVal);
                        tailSet.remove(tailIndex);
                        tailSet.add(index);
                        data.set(index, tailVal);
                    }
                    data.remove(tailIndex);
                    return true;
                }
            }
            return false;
        }

        /**
         * Get a random element from the collection.
         */
        public int getRandom() {
            return data.get((int) (Math.random() * data.size()));
        }
    }


}
