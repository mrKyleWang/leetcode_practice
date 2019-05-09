package string;

/**
 * 报数（38）
 * @author KyleWang
 * @version 1.0
 * @date 2019年05月09日
 */
public class CountAndSay {

	/*
	 * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
	 * 
	 * 1 11 21 1211 111221
	 * 
	 * 1 被读作 "one 1" ("一个一") , 即 11。
	 * 
	 * 11 被读作 "two 1s" ("两个一"）, 即 21。
	 * 
	 * 21 被读作 "one 2", "one 1" （"一个二" , "一个一") , 即 1211。
	 * 
	 * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
	 * 
	 * 注意：整数顺序将表示为一个字符串。
	 * 
	 * 输入: 1 输出: "1"
	 * 
	 * 输入: 4 输出: "1211"
	 */

	public static void main(String[] args) {
		System.out.println(countAndSay(9));
	}

	public static String countAndSay(int n) {
		String next = "1";
		for (int i = 1; i <= 30; i++) {
			String num = next;
			if (n == i) {
				return num;
			}

			StringBuilder nextSb = new StringBuilder();
			int current = 0;
			int count = 0;
			for (char c : num.toCharArray()) {
				if (current == 0) {
					current = c - 48;
					count = 1;
				} else if (c - 48 == current) {
					count++;
				} else {
					nextSb.append(count);
					nextSb.append(current);
					current = c - 48;
					count = 1;
				}
			}
			nextSb.append(count);
			nextSb.append(current);
			next = nextSb.toString();
		}
		return null;
	}
}
