package stack;

import org.junit.Test;

import java.util.Stack;

/**
 * 用递归逆序栈
 * @author KyleWang
 * @version 1.0
 * @date 2020年05月08日
 */
public class ReverseStackByRecursive {

    @Test
    public void test() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println(stack);
        reverse(stack);
        System.out.println(stack);

    }

    private void reverse(Stack<Integer> stack) {

        int last = getAndRemoveLast(stack);
        if (!stack.isEmpty()) {
            reverse(stack);
        }
        stack.push(last);

    }

    private int getAndRemoveLast(Stack<Integer> stack) {
        Integer num = stack.pop();
        if (stack.isEmpty()) {
            return num;
        }
        int last = getAndRemoveLast(stack);
        stack.push(num);
        return last;

    }
}
