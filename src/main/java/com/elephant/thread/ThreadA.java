package com.elephant.thread;
public class ThreadA extends Thread{
    public int num = 0;
    public void run(){
       synchronized (this){//在此类对象上实现同步，this指代当前对象
           for(int i = 0 ; i < 3 ; ++i)
              this.num+=i;
           notifyAll();//通知所有在这个对象上等待的线程开始执行，在这里就是通知TestNotify主线程开始执行
       }
    }
    public int getNum(){
       return this.num;
    }
}
