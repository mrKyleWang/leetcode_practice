package string;

/**
 *
 * 最长公共前缀（14）
 * @author KyleWang
 * @version 1.0
 * @date 2019年05月09日
 */
public class LongestCommonPrefix {

	/*
	 * 编写一个函数来查找字符串数组中的最长公共前缀。
	 * 
	 * 如果不存在公共前缀，返回空字符串 ""。
	 * 
	 * 输入: ["flower","flow","flight"] 输出: "fl"
	 * 
	 * 输入: ["dog","racecar","car"] 输出: "" 解释: 输入不存在公共前缀。
	 * 
	 * 说明:所有输入只包含小写字母 a-z 。
	 */

	public static void main(String[] args) {
//		String[] strs = { "flower", "a", "floight" };
		String[] strs = { };
		System.out.println(longestCommonPrefix(strs));
	}

	public static String longestCommonPrefix(String[] strs) {
		int index = 0;
		StringBuilder sb = new StringBuilder();
		if (strs.length > 0) {
			while (true) {
				char current = ' ';
				boolean allEq = true;
				for (int i = 0; i < strs.length; i++) {
					String str = strs[i];
					if (index >= str.length()) {
						allEq = false;
						break;
					} else {
						if (i == 0) {
							current = str.charAt(index);
						} else {
							if (str.charAt(index) != current) {
								allEq = false;
								break;
							}
						}
					}
				}
				if (allEq) {
					sb.append(current);
					index++;
				} else {
					break;
				}
			}
		}
		return sb.toString();
	}
}
