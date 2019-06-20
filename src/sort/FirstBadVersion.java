package sort;

/**
 * 278. 第一个错误的版本
 * @author KyleWang
 * @version 1.0
 * @date 2019年06月20日
 */
public class FirstBadVersion {

	/*
		你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。
		由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。

		假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。

		你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。

		示例:

		给定 n = 5，并且 version = 4 是第一个错误的版本。

		调用 isBadVersion(3) -> false
		调用 isBadVersion(5) -> true
		调用 isBadVersion(4) -> true

		所以，4 是第一个错误的版本。
	*/

	public static void main(String[] args) {
		System.out.println(firstBadVersion(1));
	}

	public static int firstBadVersion(int n) {
		return firstBadVersion(1, n);
	}

	private static int firstBadVersion(int start, int end) {
		int size = end - start + 1;
		if (size == 1) {
			return isBadVersion(start) ? start : -1;
		}
		// 中间值
		int middle = start + (size / 2);

		if (isBadVersion(middle)) {
			// 判断左边
			int left = firstBadVersion(start, middle - 1);
			if (left < 0) {
				return middle;
			} else {
				return left;
			}
		} else {
			// 判断右边
			if (middle == end) {
				return isBadVersion(middle) ? middle : -1;
			}
			return firstBadVersion(middle + 1, end);
		}
	}

	static boolean isBadVersion(int version) {
		return version >= 1;
	}
}
