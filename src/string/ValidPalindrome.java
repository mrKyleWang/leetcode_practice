package string;

/**
 * 验证回文串（125）
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月30日
 */
public class ValidPalindrome {

	/*
	 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
	 * 
	 * 说明：本题中，我们将空字符串定义为有效的回文串。
	 * 
	 * 示例 1: 输入: "A man, a plan, a canal: Panama" 输出: true
	 */

	public static void main(String[] args) {
		// System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
		System.out.println(isPalindrome("\"Sue,\" Tom smiles, \"Selim smote us.\""));
	}

	public static boolean isPalindrome(String s) {
		int head = 0;
		int tail = s.length() - 1;
		char[] chars = s.toCharArray();
		while (head < tail) {
			boolean headFlag = false;
			boolean tailFlag = false;
			while (head < (s.length() - 1)) {
				if (!checkChar(chars[head])) {
					head++;
				} else {
					headFlag = true;
					break;
				}
			}
			while (tail > head) {
				if (!checkChar(chars[tail])) {
					tail--;
				} else {
					tailFlag = true;
					break;
				}
			}
			if (headFlag && tailFlag && !checkEquals(chars[head], chars[tail])) {
				return false;
			}
			head++;
			tail--;
		}
		return true;
	}

	private static boolean checkChar(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
	}

	private static boolean checkEquals(char a, char b) {
		if (a >= '0' && a <= '9') {
			if (b >= '0' && b <= '9') {
				return a == b;
			} else {
				return false;
			}
		} else {
			if (b >= '0' && b <= '9') {
				return false;
			} else {
				return a == b || a - b == 32 || a - b == -32;
			}
		}
	}
}
