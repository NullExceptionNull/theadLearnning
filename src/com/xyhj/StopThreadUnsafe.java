package com.xyhj;

public class StopThreadUnsafe {
    public static User u = new User();

    public static class User{
        private int id;
        private int name;
        public User(){
            id = 0;
            name = 0;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getName() {
            return name;
        }

        public void setName(int name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User [id = " + id + ", name = " + name + "]";
        }
    }

    public static class ChangeObjectThread extends Thread{

        volatile static boolean stopme = false;

        public static void stopMe(){
            stopme = true;
        }

        @Override
        public void run() {
            while (true){
                if (stopme){
                    System.out.println("Exit by stop me");
                    break;
                }
                //对象锁
                synchronized (u){
                    int v= (int)(System.currentTimeMillis()/100);
                    u.setId(v);

                        //模拟程序干一些其他的事
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    u.setName(v);

                }
                //暂停当前线程
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (u){
                    if (u.getId() != u.getName()){
                        System.out.println(u.toString());
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new ReadObjectThread().start();
        while (true) {
            Thread t = new ChangeObjectThread();
            t.start();
            Thread.sleep(150);
            //change线程在某一刻强行退出,读线程开始读数据
            t.stop();
            //ChangeObjectThread.stopMe();
        }
    }

}
