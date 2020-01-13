package stack;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 225. 用队列实现栈
 * @author KyleWang
 * @version 1.0
 * @date 2020/01/10
 */
public class ImplementStackUsingQueues {

    @Test
    public void test(){
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        System.out.println(myStack.top());
        System.out.println(myStack.pop());

    }

    class MyStack {

        Deque<Integer> deque;

        /** Initialize your data structure here. */
        public MyStack() {
            deque = new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            deque.push(x);
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return deque.pollFirst();
        }

        /** Get the top element. */
        public int top() {
            return deque.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return deque.isEmpty();
        }
    }
}
