package array;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1122. 数组的相对排序
 * @author KyleWang
 * @version 1.0
 * @date 2020年11月15日
 */
public class RelativeSortArray {
    /*
        给你两个数组，arr1 和 arr2，
        arr2 中的元素各不相同
        arr2 中的每个元素都出现在 arr1 中
        对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。

        示例：
            输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
            输出：[2,2,2,1,4,3,3,9,6,7,19]

        提示：
            arr1.length, arr2.length <= 1000
            0 <= arr1[i], arr2[i] <= 1000
            arr2 中的元素 arr2[i] 各不相同
            arr2 中的每个元素 arr2[i] 都出现在 arr1 中
     */

    @Test
    public void test() {
        int[] arr1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = {2, 1, 4, 3, 9, 6};
        System.out.println(Arrays.toString(relativeSortArray(arr1, arr2)));
    }

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length];

        Map<Integer, Integer> map = new HashMap<>(arr2.length);
        for (int i = 0; i < arr1.length; i++) {
            map.compute(arr1[i], (key, v) -> v == null ? 1 : v + 1);
        }

        int index = 0;
        for (int i = 0; i < arr2.length; i++) {
            int num = arr2[i];
            if (map.containsKey(num)) {
                int count = map.get(num);
                for (int j = 0; j < count; j++) {
                    result[index] = num;
                    index++;
                }
                map.remove(num);
            }
        }
        int tail = index;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                result[index] = entry.getKey();
                index++;
            }
        }
        Arrays.sort(result, tail, result.length);
        return result;
    }
}
