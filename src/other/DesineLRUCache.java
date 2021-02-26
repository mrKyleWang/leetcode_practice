package other;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 146. LRU缓存机制
 * @author KyleWang
 * @version 1.0
 * @date 2020年05月25日
 */
public class DesineLRUCache {

    /*

    运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。

    获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
    写入数据 put(key, value) - 如果密钥已经存在，则变更其数据值；如果密钥不存在，则插入该组「密钥/数据值」。
    当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。

    进阶:
    你是否可以在 O(1) 时间复杂度内完成这两种操作？

    示例:
    LRUCache cache = new LRUCache( 2  );       // 缓存容量
    cache.put(1, 1);
    cache.put(2, 2);
    cache.get(1);       // 返回  1
    cache.put(3, 3);    // 该操作会使得密钥 2 作废
    cache.get(2);       // 返回 -1 (未找到)
    cache.put(4, 4);    // 该操作会使得密钥 1 作废
    cache.get(1);       // 返回 -1 (未找到)
    cache.get(3);       // 返回  3
    cache.get(4);       // 返回  4

     */
    @Test
    public void test() {
        LRUCache2 cache = new LRUCache2(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));

    }

    @Test
    public void test2() {
        LRUCache2 cache = new LRUCache2(2);
        System.out.println(cache.get(2));
        cache.put(2, 6);
        System.out.println(cache.get(1));
        cache.put(1, 5);
        cache.put(1, 2);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }


    /**
     * 使用LinkedHashMap，存储插入顺序，当capacity满了时，删除head，get/put时，会将已有数据移除，然后重新push 到tail
     */
    static class LRUCache {

        int capacity;
        LinkedHashMap<Integer, Integer> map;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new LinkedHashMap<>(capacity);
        }

        public int get(int key) {
            Integer val = map.get(key);
            if (val != null) {
                map.remove(key);
                map.put(key, val);
                return val;
            }
            return -1;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                map.remove(key);
            } else if (map.size() >= capacity) {
                Integer oldKey = map.keySet().iterator().next();
                map.remove(oldKey);
            }
            map.put(key, value);
        }
    }

    /**
     * 不使用LinkedHashMap，自实现
     */
    static class LRUCache2 {

        class LRUNode {
            int key;
            int val;
            LRUNode pre, next;

            public LRUNode() {
            }

            public LRUNode(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        int capacity;
        HashMap<Integer, LRUNode> map;
        LRUNode head, tail;


        public LRUCache2(int capacity) {
            this.capacity = capacity;
            map = new LinkedHashMap<>(capacity);
            head = new LRUNode();
            tail = new LRUNode();
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                LRUNode node = map.get(key);
                moveToHead(node);
                return node.val;
            }
            return -1;
        }

        public void put(int key, int value) {
            LRUNode node;
            if (map.containsKey(key)) {
                node = map.get(key);
                node.val = value;
            } else {
                node = new LRUNode(key, value);
                if (map.size() == capacity) {
                    removeLast();
                }
                map.put(key, node);
            }
            moveToHead(node);
        }

        private void moveToHead(LRUNode node) {
            if (node.pre != null) {
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }
            node.next = head.next;
            head.next.pre = node;
            head.next = node;
            node.pre = head;
        }

        private void removeLast() {
            LRUNode last = tail.pre;
            if (last != head) {
                last.pre.next = tail;
                tail.pre = last.pre;
                map.remove(last.key);
            }
        }
    }

}
