package stack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 133. 克隆图
 * @author KyleWang
 * @version 1.0
 * @date 2019/12/25
 */
public class CloneGraph {

    /*
        给定无向连通图中一个节点的引用，返回该图的深拷贝（克隆）。图中的每个节点都包含它的值 val（Int） 和其邻居的列表（list[Node]）。

        提示：
            节点数介于 1 到 100 之间。
            无向图是一个简单图，这意味着图中没有重复的边，也没有自环。
            由于图是无向的，如果节点 p 是节点 q 的邻居，那么节点 q 也必须是节点 p 的邻居。
            必须将给定节点的拷贝作为对克隆图的引用返回。
     */
    @Test
    public void test() {
        List<Node> list1 = new ArrayList<>();
        List<Node> list2 = new ArrayList<>();
        List<Node> list3 = new ArrayList<>();
        List<Node> list4 = new ArrayList<>();
        Node node1 = new Node(1, list1);
        Node node2 = new Node(2, list2);
        Node node3 = new Node(3, list3);
        Node node4 = new Node(4, list4);
        list1.add(node2);
        list1.add(node4);

        list2.add(node1);
        list2.add(node3);

        list3.add(node2);
        list3.add(node4);

        list4.add(node1);
        list4.add(node3);

        Node cloneGraph = cloneGraph(node1);
        System.out.println(cloneGraph);
    }

    public Node cloneGraph(Node node) {
        Map<Node, Node> map = new HashMap<>();
        return clone(node, map);
    }

    public Node clone(Node node, Map<Node, Node> map) {
        Node clone = new Node(node.val, null);
        map.put(node, clone);
        if (node.neighbors != null) {
            clone.neighbors = new ArrayList<>();
            for (Node neighbor : node.neighbors) {
                if (map.containsKey(neighbor)) {
                    clone.neighbors.add(map.get(neighbor));
                } else {
                    clone.neighbors.add(clone(neighbor, map));
                }
            }
        }
        return clone;
    }

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
        }

        public Node(int val, List<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }
    }
}
