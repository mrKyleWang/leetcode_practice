package string;

/**
 * 反转字符串（344）
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月25日
 */
public class ReverseString {

	/*
	 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
	 * 
	 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
	 */

	public static void main(String[] args) {
		char[] s = { 'h', 'e', 'l', 'l', 'o' };
		reverseString(s);
		System.out.println(s);
	}

	public static void reverseString(char[] s) {
		int length = s.length;
		for (int i = 0; i < length / 2; i++) {
			char temp = s[length - i - 1];
			s[length - i - 1] = s[i];
			s[i] = temp;
		}
	}
}
