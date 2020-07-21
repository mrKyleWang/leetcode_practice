package tree;

import org.junit.Assert;
import org.junit.Test;
import tree.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 95. 不同的二叉搜索树 II
 * @author KyleWang
 * @version 1.0
 * @date 2020年07月21日
 */
public class UniqueBinarySearchTreesII {

    /*
        给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
        示例：
            输入：3
            输出：
                [
                  [1,null,3,2],
                  [3,2,null,1],
                  [3,1,null,null,2],
                  [2,1,3],
                  [1,null,2,null,3]
                ]
            解释：
                以上的输出对应以下 5 种不同结构的二叉搜索树：

                   1         3     3      2      1
                    \       /     /      / \      \
                     3     2     1      1   3      2
                    /     /       \                 \
                   2     1         2                 3
             
        提示：
            0 <= n <= 8



         [1,2,3,4,5]
     */

    @Test
    public void test() {
        int n = 6;
        List<TreeNode> treeNodes = generateTrees(n);
        Assert.assertEquals(new UniqueBinarySearchTrees().numTrees(n), treeNodes.size());
        for (TreeNode treeNode : treeNodes) {
            System.out.println(treeNode.serialize());
        }
    }

    /**
     * 递归，每次选取范围内的某个数作为这层的root，并找到左右子树的可能性（list）
     * 左右子树重合遍历，即将可能性相乘
     */
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> nodes = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nodes.addAll(add(i, 1, n));
        }
        return nodes;
    }

    private List<TreeNode> add(int i, int start, int end) {
        List<TreeNode> nodes = new ArrayList<>();
        // 左边
        List<TreeNode> left = new ArrayList<>();
        for (int j = start; j < i; j++) {
            left.addAll(add(j, start, i - 1));
        }

        // 右边
        List<TreeNode> right = new ArrayList<>();
        for (int k = i + 1; k <= end; k++) {
            right.addAll(add(k, i + 1, end));
        }

        if (left.size() > 0) {
            for (TreeNode leftNode : left) {
                if (right.size() > 0) {
                    for (TreeNode rightNode : right) {
                        nodes.add(new TreeNode(i, leftNode, rightNode));
                    }
                } else {
                    nodes.add(new TreeNode(i, leftNode, null));
                }
            }
        } else if (right.size() > 0) {
            for (TreeNode rightNode : right) {
                nodes.add(new TreeNode(i, null, rightNode));
            }
        } else {
            nodes.add(new TreeNode(i));
        }
        return nodes;
    }
}
