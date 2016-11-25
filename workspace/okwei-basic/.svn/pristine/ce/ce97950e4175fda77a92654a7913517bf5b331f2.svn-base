package com.okwei.util;

public class StringReverse {
	public static void swap(char[] arr, int begin, int end) {
		while (begin < end) {
			char temp = arr[begin];
			arr[begin] = arr[end];
			arr[end] = temp;
			begin++;
			end--;
		}
	}

	// I love java
	public static String swapWords(String str) {
		char[] arr = str.toCharArray();
		swap(arr, 0, arr.length - 1);
		int begin = 0;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] == '-') {
				swap(arr, begin, i - 1);
				begin = i + 1;
			}
		}

		return new String(arr);
	}

}
