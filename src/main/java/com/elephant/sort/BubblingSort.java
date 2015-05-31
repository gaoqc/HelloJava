package com.elephant.sort;

import java.util.Arrays;

/**
 * 冒泡排序,下沉排序
 * 
 * @author gaoqc
 * @date May 30, 2015
 */
public class BubblingSort {
	/**
	 * 两重循环,外层每循环一次,就得到当前最大值(最小值)是什么,然后调换位置(外层决定内层排序的范围).
	 * 内层没循环一次,得到未排序内的最大值(最小值)
	 * n 个数排序需要循环的次数为 (n-1)(n-2)
	 * 耗时  n(n-1)/2
	 * @param arr
	 * @param asc true 升序,否则降序
	 */
	public static void sort(Integer[] arr, boolean asc) {
		int oc=0;
		int ic=0;
		for (int i = 0; i < arr.length - 1; i++) {//比较次数是n-1
			int swap = arr[i];
			int position = i;
			int j = i;
			oc++;
			for (j++; j < arr.length; j++) {
				Integer temp = arr[j];
				if (asc) {// 升序
					if (temp.compareTo(swap) < 0) {
						swap = temp;
						position = j;
					}

				} else {// 降序
					if (temp.compareTo(swap) > 0) {
						swap = temp;
						position = j;
					}
				}
				ic++;
			}
			arr[position] = arr[i];
			arr[i] = swap;
		}
		System.out.println("共:"+arr.length+", 外层:"+oc+",内层:"+ic);

	}

	public static void main(String[] args) {
		Integer[] arr = new Integer[] { 5, 2, 1, 4, 7, 10, 3 };
		sort(arr, false);
		System.out.println(Arrays.toString(arr));

	}
}
