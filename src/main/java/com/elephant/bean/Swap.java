package com.elephant.bean;

public class Swap {

	public static void swapInt(int a){
		a=3;
	}
	public static void swapInterge(Integer a){
		a=new Integer(3);
	}
	public static void swapString(String a){
		a="3";
	}
	public static void main(String[] args) {
		int i=4;
		swapInt(i);
		System.out.println(i);
		Integer integer=new Integer(4);
		swapInterge(integer);
		System.out.println(integer);
		String str="4";
		swapString(str);
		System.out.println(str);
	}
}
