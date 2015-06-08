package com.elephant.thread.waitnotify;


public class WordThread implements  Runnable {
    private  PrintMessage printMessage=null;
	public WordThread(PrintMessage printMessage) {
         this.printMessage=printMessage;
	}

	@Override
	public void run() {
          while(printMessage.getWords().length>printMessage.getWp()){
        	  synchronized (printMessage) {
        	  if(printMessage.iswPrint()){
					System.out.print(printMessage.getWords()[printMessage.getWpAndIncreate()]);
					printMessage.setwPrint(false);
					printMessage.notify();
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
