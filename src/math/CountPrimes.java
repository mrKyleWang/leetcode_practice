package math;

/**
 * 204. 计数质数
 * @author KyleWang
 * @version 1.0
 * @date 2019年08月17日
 */
public class CountPrimes {

    /*
        统计所有小于非负整数 n 的质数的数量。
        示例:
        输入: 10
        输出: 4
        解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
    */
    public static void main(String[] args) {
        System.out.println(countPrimes(10));
    }

    public static int countPrimes(int n) {
        int count = 0;
        boolean[] filtered = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (!filtered[i]) {
                filtered[i] = false;
                count++;
                for (int j = 1; j * i < n; j++) {
                    filtered[j * i] = true;
                }
            }
        }
        return count;
    }


}
