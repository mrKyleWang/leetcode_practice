package array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 131. 分割回文串
 * @author KyleWang
 * @version 1.0
 * @date 2021年03月07日
 */
public class PalindromePartitioning {

    @Test
    public void test() {
        System.out.println(partition("efe"));
    }

    public List<List<String>> partition(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        List<List<String>> res = new ArrayList<>();
        boolean[][] dp = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = (j - i == 1 || dp[i + 1][j - 1]) && chars[i] == chars[j];
                }
            }
        }

        dfs(res, new LinkedList<>(), chars, dp, 0);

        return res;
    }

    private void dfs(List<List<String>> res, List<String> path, char[] chars, boolean[][] dp, int pos) {
        if (pos == chars.length) {
            res.add(new ArrayList<>(path));
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = pos; i < chars.length; i++) {
                sb.append(chars[i]);
                if (dp[pos][i]) {
                    path.add(sb.toString());
                    dfs(res, path, chars, dp, i + 1);
                    path.remove(path.size() - 1);
                }
            }
        }
    }
}
