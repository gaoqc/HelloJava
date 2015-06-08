package com.elephant.thread.waitnotify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 两个线程,一个线程打印字母,一个线程打印数字,形成交替打印,如:1a2b3c4d5e6f7g8h9i
 * 
 * @author gaoqc
 * @date Jun 8, 2015
 */
public class WaitNotifyThread {
	public static void main(String[] args) throws InterruptedException {
		PrintMessage printMessage = new PrintMessage();
		ExecutorService executorService=Executors.newCachedThreadPool();
		executorService.submit(new WordThread(printMessage));
		executorService.submit(new NumThread(printMessage));
		executorService.shutdown();
//		WordThread wordThread = new WordThread(printMessage);
//		NumThread numThread = new NumThread(printMessage);
//		wordThread.start();
//		numThread.start();
	}
}
