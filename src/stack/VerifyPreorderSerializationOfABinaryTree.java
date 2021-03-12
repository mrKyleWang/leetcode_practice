package stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * 331. 验证二叉树的前序序列化
 * @author KyleWang
 * @version 1.0
 * @date 2021年03月12日
 */
public class VerifyPreorderSerializationOfABinaryTree {


    @Test
    public void test() {
        Assert.assertEquals(true, isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
    }

    @Test
    public void test2() {
        Assert.assertEquals(true, isValidSerialization("9,#,92,#,#"));
    }

    @Test
    public void test3() {
        Assert.assertEquals(false, isValidSerialization("9"));
    }

    /**
     * 使用栈，每次遇到连续两个#即将之前栈顶的元素也变成#，相当于在栈中，将叶子节点移除
     */
    public boolean isValidSerialization(String preorder) {
        String[] split = preorder.split(",");
        Stack<String> stack = new Stack<>();
        for (String s : split) {
            if (s.equals("#") && !stack.isEmpty() && stack.peek().equals("#")) {
                while (!stack.isEmpty() && stack.peek().equals("#")) {
                    stack.pop();
                    if (stack.isEmpty() || stack.pop().equals("#")) {
                        return false;
                    }
                }
            }
            stack.push(s);
        }

        return stack.size() == 1 && stack.peek().equals("#");
    }
}
