package com.elephant.thread.waitnotify;


public class NumThread implements  Runnable{
	
	private PrintMessage printMessage;
	public  NumThread(PrintMessage printMessage) {
		// TODO Auto-generated constructor stub
		this.printMessage=printMessage;
	}
	
	
	@Override
	public void run() {
		while(printMessage.getNum().length>printMessage.getNp()){
			synchronized (printMessage) {
				if(!printMessage.iswPrint()){
					System.out.print(printMessage.getNum()[printMessage.getNpAndIncreate()]);
					printMessage.setwPrint(true);
					printMessage.notifyAll();
				}else{
					try {
						printMessage.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}
	}
	

}
