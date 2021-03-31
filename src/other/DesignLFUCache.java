package other;

import org.junit.Test;

import java.util.*;

/**
 * 设计LFU缓存
 * @author KyleWang
 * @version 1.0
 * @date 2021年03月31日
 */
public class DesignLFUCache {

    @Test
    public void test() {
        int[][] ops = {{1, 1, 1}, {1, 2, 2}, {1, 3, 2}, {1, 2, 4}, {1, 3, 5}, {2, 2}, {1, 4, 4}, {2, 1}};
        System.out.println(Arrays.toString(LFU(ops, 3)));
    }


    public int[] LFU(int[][] operators, int k) {
        // write code here
        LFUCache lfu = new LFUCache(k);

        List<Integer> list = new ArrayList<>();
        for (int[] op : operators) {
            if (op[0] == 1) {
                lfu.set(op[1], op[2]);
            } else {
                list.add(lfu.get(op[1]));
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }


    class LFUCache {
        int size = 0;
        Map<Integer, Integer> data = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();
        TreeMap<Integer, LinkedHashSet<Integer>> freqMap = new TreeMap<>();

        public LFUCache(int size) {
            this.size = size;
        }

        public void set(int key, int val) {
            if (data.size() == size && !data.containsKey(key)) {
                remove();
            }
            data.put(key, val);
            addCount(key);
        }

        public int get(int key) {
            if (data.containsKey(key)) {
                addCount(key);
                return data.get(key);
            }
            return -1;
        }

        private void addCount(int key) {
            int newCount = 1;
            if (count.containsKey(key)) {
                int oldCount = count.get(key);
                LinkedHashSet<Integer> set = freqMap.get(oldCount);
                set.remove(key);
                if (set.size() == 0) {
                    freqMap.remove(oldCount);
                }
                newCount = oldCount + 1;
            }
            count.put(key, newCount);
            freqMap.computeIfAbsent(newCount, k -> new LinkedHashSet<>()).add(key);
        }

        private void remove() {
            Map.Entry<Integer, LinkedHashSet<Integer>> entry = freqMap.firstEntry();
            LinkedHashSet<Integer> set = entry.getValue();
            int removeKey = set.iterator().next();
            set.remove(removeKey);
            if (set.size() == 0) {
                freqMap.remove(entry.getKey());
            }
            count.remove(removeKey);
            data.remove(removeKey);
        }

    }
}
