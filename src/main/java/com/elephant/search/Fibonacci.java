package com.elephant.search;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * 斐波那契数列
 * 
 * @author gaoqc
 * @date Jun 3, 2015
 */
public class Fibonacci {

	public static List<Integer> list(int limit) {
		List<Integer> list = Lists.newArrayList();
		int ppre = 0;
		int pre = 1;
		for (int i = 0; i < limit; i++) {
			int current = ppre + pre;
			list.add(Integer.valueOf(current));
			ppre = pre;
			pre = current;

		}
		return list;
	}

	public static int fibonacci(int n) {
		if (n <= 1) {
			return 1;
		}
		int f1 = fibonacci(n - 1);
		int f2 = fibonacci(n - 2);
		return f1 + f2;
	}

	public static void main(String[] args) {
		// for(Integer num:list(24)){
		// System.out.println(num+",");
		// }
//		for (int i = 1; i < 11; i++) {
//			System.out.println(fibonacci(i));
//		}
		System.out.println(fibonacci(8));
	}

}
