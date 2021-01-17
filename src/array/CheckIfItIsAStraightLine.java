package array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1232. 缀点成线
 * @author KyleWang
 * @version 1.0
 * @date 2021年01月17日
 */
public class CheckIfItIsAStraightLine {

    /*
        在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，
        其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。
        请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。
     */

    @Test
    public void test() {
        int[][] coordinates = {{1, 1}, {2, 2}, {3, 4}, {4, 5}, {5, 6}, {7, 7}};
        Assert.assertTrue(checkStraightLine(coordinates));
    }

    public boolean checkStraightLine(int[][] coordinates) {
        int slopX = coordinates[1][0] - coordinates[0][0];
        int slopY = coordinates[1][1] - coordinates[0][1];
        for (int i = 2; i < coordinates.length; i++) {
            int diffX = coordinates[i][0] - coordinates[i - 1][0];
            int diffY = coordinates[i][1] - coordinates[i - 1][1];
            if (diffY * slopX != diffX * slopY) {
                return false;
            }
        }
        return true;
    }
}
