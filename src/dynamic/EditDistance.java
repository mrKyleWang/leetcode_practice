package dynamic;

/**
 * 72. 编辑距离
 * @author KyleWang
 * @version 1.0
 * @date 2019/11/27
 */
public class EditDistance {

    /*
        给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
        你可以对一个单词进行如下三种操作：
            插入一个字符
            删除一个字符
            替换一个字符
        示例 1:
            输入: word1 = "horse", word2 = "ros"
            输出: 3
            解释:
            horse -> rorse (将 'h' 替换为 'r')
            rorse -> rose (删除 'r')
            rose -> ros (删除 'e')
        示例 2:
            输入: word1 = "intention", word2 = "execution"
            输出: 5
            解释:
            intention -> inention (删除 't')
            inention -> enention (将 'i' 替换为 'e')
            enention -> exention (将 'n' 替换为 'x')
            exention -> exection (将 'n' 替换为 'c')
            exection -> execution (插入 'u')
     */

    public static void main(String[] args) {
        System.out.println(new EditDistance().minDistance("", "a"));
    }

    public int minDistance(String word1, String word2) {
        // 存储word1的前i个字符转换到word2的前j个字符的最少操作数
        int[][] tempResult = new int[word1.length() + 1][word2.length() + 1];

        // 初始值
        for (int i = 0; i <= word1.length(); i++) {
            tempResult[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) {
            tempResult[0][j] = j;
        }

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // 相等则不操作
                    tempResult[i][j] = tempResult[i - 1][j - 1];
                } else {
                    // 对比增、删、换操作
                    tempResult[i][j] = min(tempResult[i - 1][j], tempResult[i][j - 1], tempResult[i - 1][j - 1]) + 1;
                }
            }
        }
        return tempResult[word1.length()][word2.length()];
    }

    private int min(int... nums) {
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min = min < nums[i] ? min : nums[i];
        }
        return min;
    }
}
