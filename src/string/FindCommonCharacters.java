package string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 1002. 查找常用字符
 * @author KyleWang
 * @version 1.0
 * @date 2020年10月15日
 */
public class FindCommonCharacters {

    /*
        给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
        例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。

        你可以按任意顺序返回答案。

        示例 1：
            输入：["bella","label","roller"]
            输出：["e","l","l"]
        示例 2：
            输入：["cool","lock","cook"]
            输出：["c","o"]

        提示：
            1 <= A.length <= 100
            1 <= A[i].length <= 100
            A[i][j] 是小写字母
     */

    @Test
    public void test() {
        String[] A = {"bella", "label", "roller"};
        System.out.println(commonChars(A));
    }

    @Test
    public void test2() {
        String[] A = {"cool", "lock", "cook"};
        System.out.println(commonChars(A));
    }

    public List<String> commonChars(String[] A) {
        List<String> result = new ArrayList<>();
        int[][] temp = new int[26][A.length];

        for (int i = 0; i < A.length; i++) {
            for (char c : A[i].toCharArray()) {
                temp[c - 'a'][i]++;
            }
        }

        for (int i = 0; i < 26; i++) {
            int min = Integer.MAX_VALUE;
            for (int count : temp[i]) {
                if (count == 0) {
                    min = 0;
                    break;
                }
                min = Math.min(min, count);
            }
            if (min > 0) {
                for (int j = 0; j < min; j++) {
                    result.add((char) (i + 'a') + "");
                }
            }
        }
        return result;
    }
}
