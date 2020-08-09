package string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. 复原IP地址
 * @author KyleWang
 * @version 1.0
 * @date 2020年08月09日
 */
public class RestoreIPAddresses {

    /*
        给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
        有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。

        示例:
            输入: "25525511135"
            输出: ["255.255.11.135", "255.255.111.35"]
     */

    @Test
    public void test() {
        String s = "25525511135";
        System.out.println(restoreIpAddresses(s));
    }

    @Test
    public void test2() {
        String s = "0000";
        System.out.println(restoreIpAddresses(s));
    }

    /**
     * 深度优先，回溯
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        dfs(result, s, 0, 0, "");
        return result;
    }

    public void dfs(List<String> result, String s, int depth, int index, String temp) {
        if (depth < 4 && index < s.length()) {
            for (int end = index + 1; end <= index + 3 && end <= s.length(); end++) {
                String substring = s.substring(index, end);
                int num = Integer.parseInt(substring);
                if (num <= 255) {
                    String cur = depth == 0 ? substring : temp + "." + substring;
                    if (end == s.length() && depth == 3) {
                        result.add(cur);
                    } else {
                        dfs(result, s, depth + 1, end, cur);
                    }
                    if (num == 0) {
                        break;
                    }
                }
            }
        }
    }
}
