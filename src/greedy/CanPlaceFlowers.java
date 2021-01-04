package greedy;

import org.junit.Assert;
import org.junit.Test;

/**
 * 605. 种花问题
 * @author KyleWang
 * @version 1.0
 * @date 2021年01月04日
 */
public class CanPlaceFlowers {

    /*
        假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
        给你一个整数数组  flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。
        另有一个数 n ，能否在不打破种植规则的情况下种入 n 朵花？能则返回 true ，不能则返回 false。

        示例 1：
            输入：flowerbed = [1,0,0,0,1], n = 1
            输出：true
        示例 2：
            输入：flowerbed = [1,0,0,0,1], n = 2
            输出：false
         
        提示：
            1 <= flowerbed.length <= 2 * 104
            flowerbed[i] 为 0 或 1
            flowerbed 中不存在相邻的两朵花
            0 <= n <= flowerbed.length
     */


    @Test
    public void test() {
        int[] flowerbed = {1, 0, 0, 0, 1};
        Assert.assertEquals(true, canPlaceFlowers(flowerbed, 1));
    }

    @Test
    public void test2() {
        int[] flowerbed = {1, 0, 0, 0, 1};
        Assert.assertEquals(false, canPlaceFlowers(flowerbed, 2));
    }

    @Test
    public void test3() {
        int[] flowerbed = {1, 0, 0, 0, 0, 1};
        Assert.assertEquals(false, canPlaceFlowers(flowerbed, 2));
    }

    @Test
    public void test4() {
        int[] flowerbed = {1, 0, 0, 0, 1, 0, 0};
        Assert.assertEquals(true, canPlaceFlowers(flowerbed, 2));
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        int pre = 0;
        int next = flowerbed[0];
        for (int i = 0; i < flowerbed.length; i++) {
            int cur = next;
            next = i == flowerbed.length - 1 ? 0 : flowerbed[i + 1];
            if (pre == 0 && cur == 0 && next == 0) {
                flowerbed[i] = 1;
                count++;
            }
            if (count >= n) {
                return true;
            }
            pre = flowerbed[i];
        }
        return false;
    }
}
