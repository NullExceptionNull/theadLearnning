package com.xyhj;

public class InterruptDemo {

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                while (true){
                    Thread.yield();
                    System.out.println("////");
                }
            }
        };
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }

//    public static void main(String[] args)throws InterruptedException {
//        Thread t1 = new Thread(){
//            @Override
//            public void run() {
//                while (true){
//                    if (Thread.currentThread().isInterrupted()){
//                        System.out.println(Thread.currentThread().getName());
//                        System.out.println("Interrupt");
//                        break;
//                     }
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        System.out.println("线程在睡眠时被中断");
//                        Thread.currentThread().interrupt();
//                    }
//                    System.out.println(".....");
//                    Thread.yield();
//                }
//            }
//        };
//        t1.start();
//        Thread.sleep(1000);
//        t1.interrupt();
//    }

}
