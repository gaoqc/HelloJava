package com.elephant.thread;

public class TestNotify{
    public static void main(String args[]){
       ThreadA threada = new ThreadA();
       threada.start();//threada线程有执行的资格，但是还没有开始执行
       synchronized(threada){
           try{
              threada.wait();//主线程等待threada线程执行结束才开始执行
              //而且只有获得了当前threada对象的锁之后才能执行wait，就是说在同步域内才可以执行wait，执行wait后放弃对象锁
           }catch(InterruptedException e){
              e.printStackTrace();
           }
       }
       System.out.println(threada.getNum());
    }
}
