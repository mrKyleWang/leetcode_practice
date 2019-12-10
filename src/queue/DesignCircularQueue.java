package queue;

import org.junit.Test;


/**
 * 622. 设计循环队列
 * @author KyleWang
 * @version 1.0
 * @date 2019/12/10
 */
public class DesignCircularQueue {

    @Test
    public void test() {
        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
        System.out.println(circularQueue.enQueue(1));   // 返回 true
        System.out.println(circularQueue.enQueue(2));   // 返回 true
        System.out.println(circularQueue.enQueue(3));   // 返回 true
        System.out.println(circularQueue.enQueue(4));   // 返回 false，队列已满
        System.out.println(circularQueue.Rear());   // 返回 3
        System.out.println(circularQueue.isFull()); // 返回 true
        System.out.println(circularQueue.deQueue());    // 返回 true
        System.out.println(circularQueue.enQueue(4));   // 返回 true
        System.out.println(circularQueue.Rear());   // 返回 4
    }

    @Test
    public void test2() {
        MyCircularQueue circularQueue = new MyCircularQueue(6);
        System.out.println(circularQueue.enQueue(6));
        System.out.println(circularQueue.Rear());
        System.out.println(circularQueue.Rear());
        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.enQueue(5));
        System.out.println(circularQueue.Rear());
        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.Front());
        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.deQueue());

    }

    class MyCircularQueue {

        int count = 0;
        int head = -1;
        int tail = -1;
        int[] queue;

        /**
         * Initialize your data structure here. Set the size of the queue to be k.
         */
        public MyCircularQueue(int k) {
            queue = new int[k];
        }

        /**
         * Insert an element into the circular queue. Return true if the operation is successful.
         */
        public boolean enQueue(int value) {
            if (count < queue.length) {
                if (head == -1) {
                    head = 0;
                    tail = 0;
                } else {
                    if (tail == queue.length - 1) {
                        tail = 0;
                    } else {
                        tail++;
                    }
                }
                queue[tail] = value;
                count++;
                return true;
            }
            return false;
        }

        /**
         * Delete an element from the circular queue. Return true if the operation is successful.
         */
        public boolean deQueue() {
            if (count > 0) {
                if (head == queue.length - 1) {
                    head = 0;
                } else {
                    head++;
                }
                count--;
                return true;
            }
            return false;
        }

        /**
         * Get the front item from the queue.
         */
        public int Front() {
            return count == 0 ? -1 : queue[head];
        }

        /**
         * Get the last item from the queue.
         */
        public int Rear() {
            return count == 0 ? -1 : queue[tail];
        }

        /**
         * Checks whether the circular queue is empty or not.
         */
        public boolean isEmpty() {
            return count == 0;
        }

        /**
         * Checks whether the circular queue is full or not.
         */
        public boolean isFull() {
            return count == queue.length;
        }
    }

}
