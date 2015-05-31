package com.elephant.sort;

import java.util.Arrays;

/**
 * 快速排序 最坏情况下的排序时间与冒泡时间一样n(n-1)/2,最好情况下(假如已经排好序),耗时是n-1
 * 
 * @author gaoqc
 * @date May 30, 2015
 */
public class QuickSort {
	/**
	 *  快速排序:外层控制最右边的排序下标,内层每次从最左边开始,与下一个数比较,如果比他大(比他小),则立即交换位置.
	 *  
	 * @param arr
	 * @param asc true 升序,否则降序
	 * 
	 */
	public static void sort(Integer[] arr, boolean asc) {
		boolean swap = false;
		int ic = 0;
		int oc = 0;
		for (int i = arr.length; i > 1; i--) {
			oc++;
			for (int j = 1; j < i; j++) {
				if (asc && arr[j - 1] > arr[j]) {
					swap(arr, j - 1, j);
					swap = true;
				} else if (arr[j - 1] < arr[j]) {
					swap(arr, j - 1, j);
					swap = true;
				}
				ic++;
			}
			if (!swap) {
				break;
			}
		}
		System.out.println("共:" + arr.length + ", 外层:" + oc + ",内层:" + ic);
	}

	private static void swap(Integer[] arr, int p1, int p2) {
		Integer temp = arr[p1];
		arr[p1] = arr[p2];
		arr[p2] = temp;
	}

	public static void main(String[] args) {
		Integer[] arr = new Integer[] { 6, 5, 4, 3, 2, 1 };
		sort(arr, false);
		System.out.println(Arrays.toString(arr));
	}
}
