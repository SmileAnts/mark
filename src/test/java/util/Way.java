package util;

public class Way {

	public static int funBinSearch(int[] array, int data) {

		int low = 0; /* 定义最底下标为记录首位 */
		int high = array.length - 1;/* 定义最高下标为记录末位 */

		while (low <= high) {
			int mid = (low + high) / 2; /* 折半 */
			if (data == array[mid]) {
				return mid;
			} else if (data < array[mid]) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 66 };
		int data = 66; 

		// 预想结果return 4 ，位置是第四个
		System.out.println(funBinSearch(array, data));
	}

}
