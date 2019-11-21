package com.smile;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.druid.util.StringUtils;

public class Arithmetic {

	public static void main(String[] args) {
		String s = "9646324351";
		StringUtils.stringToInteger(s);
		int target = -10024;
		reverse(target);
	}

	/**
	 * 递归翻转
	 * 
	 * <pre>
	 * String[] str = new String[10];
	 * str[0] = "第一个";
	 * str[1] = "第二个";
	 * str[2] = "第仨个";
	 * str[3] = "第四个";
	 * str[4] = "第伍个";
	 * str[5] = "第陆个";
	 * str[6] = "第柒个";
	 * str[7] = "第捌个";
	 * str[8] = "第玖个";
	 * str[9] = "第十个";
	 * printReverse(str);
	 * </pre>
	 */
	@SuppressWarnings("unused")
	private static void printReverse(String[] str) {
		helper(0, str);
	}

	private static void helper(int index, String[] str) {
		if (str == null || index >= str.length) {
			return;
		}
		helper(index + 1, str);
		System.out.print(str[index]);
	}

	/**
	 * 找出数组中连续的最长 空间复杂度：O(n) 时间复杂度：O(n)
	 */
	@SuppressWarnings("unused")
	private static int longestConsecutive(int[] nums) {
		Set<Integer> num_set = new HashSet<Integer>();
		for (int num : nums) {
			num_set.add(num);
		}
		int longestStreak = 0;
		for (int num : num_set) {
			if (!num_set.contains(num - 1)) {
				int currentNum = num;
				int currentStreak = 1;
				while (num_set.contains(currentNum + 1)) {
					currentNum += 1;
					currentStreak += 1;
				}
				longestStreak = Math.max(longestStreak, currentStreak);
			}
		}
		return longestStreak;
	}

	/**
	 * 寻找 和为target 的下标
	 * 
	 * <pre>
	 * 		将数组中的值作为key，下标作为value
	 * 		最后查看map中是否能找到key，则存在这个数，value则为下标
	 * </pre>
	 * 
	 * @param nums   int[] test = new int[] { 10000, 0, 1, 2, 10, 10001,22, 24 };
	 * @param target 26
	 * @return
	 */
	@SuppressWarnings("unused")
	private static int[] twoSum(int[] nums, int target) {
		int max = 2047;
		int[] map = new int[2048];
		for (int i = 0; i < nums.length; i++) {
			int dif = map[(target - nums[i]) & max];
			if (dif != 0) {
				return new int[] { dif - 1, i };
			}
			map[(nums[i]) & max] = i + 1;
		}
		return null;
	}

	private static int reverse(int arg) {
		int resultNum = 0;
		String tempResult = "";
		if (arg > 0) {
			tempResult = Integer.toString(arg);
			String[] tempArray = tempResult.split("");
			String[] tempResultArray = new String[tempArray.length];
			for (int i = 0; i < tempArray.length; i++) {
				tempResultArray[i] = tempArray[tempArray.length - i - 1];
			}
			String result = "";
			for (String string : tempResultArray) {
				result = result + string;
			}
			resultNum = Integer.valueOf(result);
		} else {
			tempResult = Integer.toString(Math.abs(arg));
			String[] tempArray = tempResult.split("");
			String[] tempResultArray = new String[tempArray.length];
			for (int i = 0; i < tempArray.length; i++) {
				tempResultArray[i] = tempArray[tempArray.length - i - 1];
			}
			String result = "-";
			for (String string : tempResultArray) {
				result = result + string;
			}
			resultNum = Integer.valueOf(result);
		}
		return resultNum;
	}
}
