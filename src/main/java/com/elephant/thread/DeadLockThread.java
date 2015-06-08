package com.elephant.thread;

import tools.time.ThreadUtil;

public class DeadLockThread {

	private Object obj1 = new Object();
//	@Resource(name=)
	private Object obj2 = new Object();

	public void lockObj1thenLockObj2() {
		
		synchronized (obj1) {

			synchronized (obj1) {
				System.out.println("1lock obj1");
				ThreadUtil.sleepMilliSeconds(5);
				synchronized (obj2) {
					System.out.println("1lock obj2");
					ThreadUtil.sleepMilliSeconds(5);
				}
			}

		}
	}

	public void lockObj2ThenLockObj1() {
		synchronized (obj2) {
			System.out.println("2lock obj2");
			ThreadUtil.sleepMilliSeconds(5);
			synchronized (obj1) {
				System.out.println("2lock obj1");

				ThreadUtil.sleepMilliSeconds(5);
			}
		}

	}

	public static void main(String[] args) {
		final DeadLockThread deadThread = new DeadLockThread();
		new Thread() {
			@Override
			public void run() {
				deadThread.lockObj1thenLockObj2();
			}
		}.start();
		new Thread() {
			public void run() {
				deadThread.lockObj2ThenLockObj1();
			};
		}.start();

	}
}
