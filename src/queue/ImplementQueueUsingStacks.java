package queue;

import org.junit.Test;

import java.util.Stack;

/**
 * 232. 用栈实现队列
 * @author KyleWang
 * @version 1.0
 * @date 2020/01/06
 */
public class ImplementQueueUsingStacks {

    /*
        使用栈实现队列的下列操作：
            push(x) -- 将一个元素放入队列的尾部。
            pop() -- 从队列首部移除元素。
            peek() -- 返回队列首部的元素。
            empty() -- 返回队列是否为空。
        示例:
            MyQueue queue = new MyQueue();
            queue.push(1);
            queue.push(2);
            queue.peek();  // 返回 1
            queue.pop();   // 返回 1
            queue.empty(); // 返回 false
        说明:
            你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
            你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
            假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
     */

    @Test
    public void test(){
        MyQueue obj = new MyQueue();
        obj.push(1);
        obj.push(2);
        System.out.println(obj.peek());
        obj.push(3);
        System.out.println(obj.peek());
        System.out.println(obj.pop());

        obj.push(2);
        System.out.println(obj.peek());
        System.out.println(obj.pop());
        System.out.println(obj.empty());
    }

    class MyQueue {

        private int size;
        private Stack<Integer> headStack;
        private Stack<Integer> tailStack;

        /** Initialize your data structure here. */
        public MyQueue() {
            headStack = new Stack<>();
            tailStack = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            headStack.push(x);
            size++;
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (tailStack.size()==0){
                move();
            }
            size --;
            return tailStack.pop();
        }

        /** Get the front element. */
        public int peek() {
            if (tailStack.size()==0){
                move();
            }
            return tailStack.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return size <= 0;
        }

        private void move(){
            for (int i = 0; i < size; i++) {
                tailStack.push(headStack.pop());
            }
        }
    }
}
