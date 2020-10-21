package string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 925. 长按键入
 * @author KyleWang
 * @version 1.0
 * @date 2020年10月21日
 */
public class LongPressedName {

    /*
    你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
    你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。

    示例 1：
        输入：name = "alex", typed = "aaleex"
        输出：true
        解释：'alex' 中的 'a' 和 'e' 被长按。
    示例 2：
        输入：name = "saeed", typed = "ssaaedd"
        输出：false
        解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
    示例 3：
        输入：name = "leelee", typed = "lleeelee"
        输出：true
    示例 4：
        输入：name = "laiden", typed = "laiden"
        输出：true
        解释：长按名字中的字符并不是必要的。

    提示：
        name.length <= 1000
        typed.length <= 1000
        name 和 typed 的字符都是小写字母。
     */


    @Test
    public void test() {
        String name = "alex";
        String typed = "aaleex";
        Assert.assertEquals(true, isLongPressedName(name, typed));
    }

    @Test
    public void test2() {
        String name = "saeed";
        String typed = "ssaaedd";
        Assert.assertEquals(false, isLongPressedName(name, typed));
    }

    @Test
    public void test3() {
        String name = "leelee";
        String typed = "lleeelee";
        Assert.assertEquals(true, isLongPressedName(name, typed));
    }

    @Test
    public void test4() {
        String name = "laiden";
        String typed = "laiden";
        Assert.assertEquals(true, isLongPressedName(name, typed));
    }

    @Test
    public void test5() {
        String name = "kikcxmvzi";
        String typed = "kiikcxxmmvvzz";
        Assert.assertEquals(false, isLongPressedName(name, typed));
    }

    @Test
    public void test6() {
        String name = "vtkgn";
        String typed = "vttkgnn";
        Assert.assertEquals(true, isLongPressedName(name, typed));
    }

    public boolean isLongPressedName(String name, String typed) {
        int i = 0;
        int j = 0;
        char[] nameChars = name.toCharArray();
        char[] typedChars = typed.toCharArray();
        while (i < nameChars.length && j < typedChars.length) {
            if (nameChars[i] == typedChars[j]) {
                i++;
                j++;
            } else if (j > 0 && typedChars[j] == typedChars[j - 1]) {
                j++;
            } else {
                return false;
            }
        }
        while (j > 0 && j < typedChars.length && typedChars[j] == typedChars[j - 1]) {
            j++;
        }
        return i == nameChars.length && j == typedChars.length;
    }
}
