package string;

/**
 * 整数反转（7）
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月25日
 */
public class ReverseInteger {

	/*
	 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
	 */

	public static void main(String[] args) {
		System.out.println(reverse(-1230));
	}

	public static int reverse(int x) {
		char[] chars = String.valueOf(x).toCharArray();
		StringBuilder sb = new StringBuilder();
		if (x < 0) {
			sb.append('-');
		}
		boolean skipZero = true;
		for (int i = chars.length - 1; i >= 0; i--) {
			char c = chars[i];
			if (c == '-') {
				continue;
			}
			if (c == '0') {
				if (!skipZero) {
					sb.append(c);
				}
			} else {
				skipZero = false;
				sb.append(c);
			}
		}
		int i = 0;
		try {
			i = Integer.parseInt(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
}
