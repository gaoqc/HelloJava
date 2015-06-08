package com.elephant.thread.waitnotify;

import tools.time.ThreadUtil;

public class InitConfig implements Runnable {
	public void run() {
         ThreadUtil.sleepSecods(3);
         synchronized (this) {
			
        	 notifyAll();
		}
         System.out.println("init config finish!");
         
	}

}
