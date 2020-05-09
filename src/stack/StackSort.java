package stack;

import org.junit.Test;

import java.util.Stack;

/**
 * 用一个栈实现另一个栈的排序
 * @author KyleWang
 * @version 1.0
 * @date 2020年05月09日
 */
public class StackSort {

    @Test
    public void test() {
        Stack<Integer> stack = new Stack<>();

        stack.push(5);
        stack.push(4);
        stack.push(2);
        stack.push(3);
        stack.push(1);

        sortStackByStack(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    private void sortStackByStack(Stack<Integer> stack) {
        Stack<Integer> help = new Stack<>();
        while (!stack.isEmpty()) {
            Integer cur = stack.pop();
            while (!help.isEmpty() && cur > help.peek()) {
                stack.push(help.pop());
            }
            help.push(cur);
        }

        while (!help.isEmpty()) {
            stack.push(help.pop());
        }

    }
}
