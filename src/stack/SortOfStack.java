package stack;

import org.junit.Test;

import java.util.Stack;

/**
 * 面试题 03.05. 栈排序
 * @author KyleWang
 * @version 1.0
 * @date 2020年05月09日
 */
public class SortOfStack {

    /*
        栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。
        最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。
        该栈支持如下操作：push、pop、peek 和 isEmpty。当栈为空时，peek 返回 -1。


     */

    @Test
    public void test() {
        SortedStack stack = new SortedStack();
        stack.push(1);
        stack.push(2);
        stack.push(4);
        stack.push(3);
        stack.push(5);
        while (!stack.isEmpty()) {
            System.out.println(stack.peek());
            stack.pop();
        }
//        System.out.println(stack.peek());
//        stack.pop();
//        System.out.println(stack);
//        System.out.println(stack.peek());
    }

    class SortedStack {

        Stack<Integer> stack;
        Stack<Integer> help;

        public SortedStack() {
            stack = new Stack<>();
            help = new Stack<>();
        }

        public void push(int val) {
            while (!stack.isEmpty() && stack.peek() < val) {
                Integer cur = stack.pop();
                while (!help.isEmpty() && cur < help.peek()) {
                    stack.push(help.pop());
                }
                help.push(cur);
            }
            stack.push(val);
            while (!help.isEmpty()) {
                stack.push(help.pop());
            }
        }

        public void pop() {
            if (!stack.isEmpty()) {
                stack.pop();
            }
        }

        public int peek() {
            return stack.isEmpty() ? -1 : stack.peek();
        }

        public boolean isEmpty() {
            return stack.isEmpty();
        }
    }
}
