package com.elephant.thread.waitnotify;

public class PrintMessage {
	private char[] words = "abcdefghi".toCharArray();
	private int wp = 0;
	private char[] num = "123456789".toCharArray();
	private int np = 0;

	private boolean wPrint = false;

	public char[] getWords() {
		return words;
	}

	public void setWords(char[] words) {
		this.words = words;
	}

	public int getWp() {
		return wp;
	}

	public int getWpAndIncreate() {
		int t = getWp();
		wp++;
		return t;
	}

	public int getNpAndIncreate() {
		int t = getNp();
		np++;
		return t;

	}

	public void setWp(int wp) {
		this.wp = wp;
	}

	public void setNum(char[] num) {
		this.num = num;
	}

	public char[] getNum() {
		return num;
	}

	public int getNp() {
		return np;
	}

	public void setNp(int np) {
		this.np = np;
	}

	public boolean iswPrint() {
		return wPrint;
	}

	public void setwPrint(boolean wPrint) {
		this.wPrint = wPrint;
	}

}
