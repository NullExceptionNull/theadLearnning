package com.xyhj.syn;

/**
 * Created by zhangjianghong on 2017/8/1.
 */
public class SynClass implements Runnable{
    static volatile int i = 0;
    static SynClass synClass = new SynClass();



    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            synchronized (SynClass.class){
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(synClass);
        Thread t2 = new Thread(synClass);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
