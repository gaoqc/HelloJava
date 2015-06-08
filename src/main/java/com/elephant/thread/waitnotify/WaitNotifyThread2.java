package com.elephant.thread.waitnotify;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一个子线程:需要读取配置,必须读取完成,主线程才允许走下去
 * @author gaoqc
 * @date Jun 8, 2015
 */
public class WaitNotifyThread2 {

	public static void main(String[] args) throws InterruptedException {
		Lock lock= new ReentrantLock();
		Thread initThread=new Thread(new InitConfig());
		initThread.start();
		try{	
			lock.lock();
			initThread.wait();
		} finally{
			lock.unlock();
		}
	   System.out.println("main thread finish!");	
		
	}
}
