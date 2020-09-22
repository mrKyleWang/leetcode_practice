package dynamic;

import org.junit.Assert;
import org.junit.Test;
import tree.entity.TreeNode;

/**
 * 968. 监控二叉树
 * @author KyleWang
 * @version 1.0
 * @date 2020年09月22日
 */
public class BinaryTreeCameras {


    /*
        给定一个二叉树，我们在树的节点上安装摄像头。
        节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
        计算监控树的所有节点所需的最小摄像头数量。

         

        示例 1：
                0
               /
              X
             / \
            0   0

            输入：[0,0,null,0,0]
            输出：1
            解释：如图所示，一台摄像头足以监控所有节点。
        示例 2：
                    0
                   /
                  X
                 /
                0
               /
              X
               \
                0

            输入：[0,0,null,0,null,0,null,null,0]
            输出：2
            解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
     */

    @Test
    public void test() {
        TreeNode root = TreeNode.deserialize("0,0,null,0,0");
        Assert.assertEquals(1, minCameraCover(root));
    }

    @Test
    public void test2() {
        TreeNode root = TreeNode.deserialize("0,0,null,0,null,0,null,null,0");
        Assert.assertEquals(2, minCameraCover(root));
    }

    @Test
    public void test3() {
        TreeNode root = TreeNode.deserialize("1,2,3,null,null,4");
        Assert.assertEquals(2, minCameraCover(root));
    }

    /**
     * 动态规划：
     * 每个节点3种状态：0：有摄像头，1：保证本身可被监测，2：保证子节点可被监测
     * 三种状态关系一定保证：dp[0] >= dp[1] >= dp[2]
     */
    public int minCameraCover(TreeNode root) {
        int[] minCamera = minCamera(root);
        return minCamera[1];
    }

    public int[] minCamera(TreeNode root) {
        if (root == null) {
            return new int[]{Integer.MAX_VALUE / 2, 0, 0};
        }
        int[] leftMin = minCamera(root.left);
        int[] rightMin = minCamera(root.right);
        int[] cur = new int[3];
        cur[0] = leftMin[2] + rightMin[2] + 1;
        cur[1] = Math.min(cur[0], Math.min(leftMin[0] + rightMin[1], leftMin[1] + rightMin[0]));
        cur[2] = Math.min(cur[0], leftMin[1] + rightMin[1]);
        return cur;
    }

}
