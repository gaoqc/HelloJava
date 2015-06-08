package com.elephant.search;

import java.util.Arrays;

public class BinarySearch {
	public static int search(Integer[] arr, Integer num) {
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.binarySearch(arr, num));
		return searchWithSortArray(arr, num, 0, arr.length);
	}

	private static int searchWithSortArray(Integer[] arr, Integer key, int low, int high) {
           high--;
		while(low<=high){
			int mid=(low+high)/2;
			int midVal=arr[mid];
			if(key<midVal){
				high=mid-1;
				continue;
			}else if(key>midVal){
				low=mid+1;
				continue;
			}else{
				return mid;
			}
		}
		return -(low+1);
       
	}

	public static void main(String[] args) {
		Integer[] arr = new Integer[] { 5, 3, 8, 10, 15, 7, 2 };
		System.out.println(search(arr, 8));
//		System.out.println(  (1+7) >> 1);
	}
}
