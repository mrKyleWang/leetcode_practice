package stack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 汉诺塔问题
 * @author KyleWang
 * @version 1.0
 * @date 2020年05月12日
 */
public class Hanota {

    @Test
    public void test() {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();

        list1.add(4);
        list1.add(3);
        list1.add(2);
        list1.add(1);
        list1.add(0);

        hanota(list1, list2, list3);
        System.out.println(list3);
    }

    /**
     * 1、要将n个数从A->C（两边移动）：
     *  (1)0~n-1：A->B
     *  (2)n：A->C
     *  (3)0~n-1：B->C
     * 2、将n个数从A->B（边缘移到中间）：
     *  (1)0~n-1：A->C
     *  (2)n：A->B
     *  (3)0~n-1：C->B
     * 2、将n个数从B->A（中间移到边缘）：
     *  (1)0~n-1：B->C
     *  (2)n：B->A
     *  (3)0~n-1：C->A
     */
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        handle(A.size(), A, B, C);
    }

    private void handle(int n, List<Integer> source, List<Integer> mid, List<Integer> target) {
        if (n == 0) {
            return;
        }
        if (n == 1) {
            move(source.size() - 1, source, target);
            return;
        }
        handle(n - 1, source, target, mid);
        move(source.size() - 1, source, target);
        handle(n - 1, mid, source, target);
    }

    private void move(int index, List<Integer> source, List<Integer> target) {
        Integer num = source.get(index);
        source.remove(index);
        target.add(num);
    }
}
