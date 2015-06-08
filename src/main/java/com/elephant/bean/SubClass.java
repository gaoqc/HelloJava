package com.elephant.bean;

public class SubClass extends SuperClass {

	public SubClass(){
		test();
	}
	public void test(){
		System.out.println("subClass out");
	}
	public static void main(String[] args) {
		SubClass subClass=new SubClass();
	}
	
}
