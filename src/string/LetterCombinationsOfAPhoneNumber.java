package string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 17. 电话号码的字母组合
 * @author KyleWang
 * @version 1.0
 * @date 2020年06月30日
 */
public class LetterCombinationsOfAPhoneNumber {

    /*
        给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
        给出数字到字母的映射与电话按键相同。注意 1 不对应任何字母。：
            2 abc
            3 def
            4 ghi
            5 jkl
            6 mno
            7 pqrs
            8 tuv
            9 wxyz

        示例:
            输入："23"
            输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
            说明:
            尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
     */

    @Test
    public void test(){
        List<String> list = letterCombinations("2");
        for (String s : list) {
            System.out.println(s);
        }
    }

    char[][] dict= {{'a','b','c'},
                    {'d','e','f'},
                    {'g','h','i'},
                    {'j','k','l'},
                    {'m','n','o'},
                    {'p','q','r','s'},
                    {'t','u','v'},
                    {'w','x','y','z'}};

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        Set<String> set = new LinkedHashSet<>();
        add(set, new char[digits.length()], digits.toCharArray(), 0);
        return new ArrayList<>(set);
    }

    /**
     * 回溯算法，记录每个选择的路径，在最后一个位置组合成String
     */
    private void add(Set<String> set, char[] path, char[] arr, int cur) {
        if (cur == arr.length) {
            set.add(new String(path));
        } else {
            int index = arr[cur] - 50;
            for (char c : dict[index]) {
                path[cur] = c;
                add(set, path, arr, cur + 1);
            }
        }
    }
}
