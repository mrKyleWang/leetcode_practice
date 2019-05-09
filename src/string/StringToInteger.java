package string;

/**
 * 字符串转换整数-atoi（8）
 * @author KyleWang
 * @version 1.0
 * @date 2019年05月08日
 */
public class StringToInteger {

	/*
	 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
	 * 
	 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，
	 * 则直接将其与之后连续的数字字符组合起来，形成整数。
	 * 
	 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
	 * 
	 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
	 * 
	 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
	 */

	public static void main(String[] args) {
		System.out.println(myAtoi("+"));
	}

	public static int myAtoi(String str) {
		str = str.trim();
		char[] chars = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		int startIndex = 0;
		boolean minus = false;
		boolean hasNum = false;

		if (chars.length > 0) {
			if (chars[startIndex] == '+') {
				startIndex++;
			} else if (chars[startIndex] == '-') {
				minus = true;
				sb.append(chars[startIndex]);
				startIndex++;
			}
			if (startIndex >= chars.length || !checkNum(chars[startIndex])) {
				return 0;
			}

			boolean numStart = false;
			for (int i = startIndex; i < chars.length; i++) {
				if (numStart && chars[i] == '.') {
					break;
				}
				if ((!numStart && chars[i] == '0')) {
					continue;
				}
				if (checkNum(chars[i])) {
					sb.append(chars[i]);
					hasNum = true;
					if (chars[i] != '0') {
						numStart = true;
					}
				} else {
					break;
				}
			}
			if (hasNum && sb.length() > 0) {
				String numStr = sb.toString();
				if (sb.length() > 11) {
					if (minus) {
						return Integer.MIN_VALUE;
					} else {
						return Integer.MAX_VALUE;
					}
				} else {
					long parseLong = Long.parseLong(numStr);
					if (parseLong < Integer.MIN_VALUE) {
						return Integer.MIN_VALUE;
					} else if (parseLong > Integer.MAX_VALUE) {
						return Integer.MAX_VALUE;
					} else {
						return (int) parseLong;
					}
				}
			}
		}
		return 0;
	}

	private static boolean checkNum(char c) {
		return c >= '0' && c <= '9';
	}
}
