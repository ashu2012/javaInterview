package com.stocknap;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/*

This program prints even and odd sequence using two thread with intercommunication of wait and notify

 */


public class MultithreadingCommunication {

    static Semaphore lck  = new Semaphore(1,true);
    static volatile AtomicInteger currThd = new AtomicInteger(0);
    static  class evenThread implements  Runnable{

        String msg;

        public void setAnother(String t){
            this.msg =  t;
        }

        @Override
        public void run() {

            while(currThd.get() < 10){

                synchronized (msg) {
                    if (currThd.get() % 2 == 0) {
                        try {
                            System.out.println("acquiring lock by even thread");
                            lck.acquire();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(currThd);
                        currThd.addAndGet(1);
                        System.out.println("value increment by thd");
                        lck.release();
                        System.out.println("lck released  by thd");
                        try {
                            msg.notify();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            msg.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }


        }



    }

    static class oddThread implements  Runnable{

        String msg;

        public void setAnother(String t){
            this.msg =  t;
        }

        @Override
        public void run() {
            while(currThd.get() <= 10){

                synchronized (msg){
                    if (currThd.get() % 2!=0  ) {
                        try {
                            System.out.println("acquiring lock by odd thread");
                            lck.acquire();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(currThd);
                        currThd.addAndGet(1);
                        lck.release();
                        try {
                            msg.notify();
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    else{
                        try {
                            msg.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }



            }



        }



    }
    public static void runFlow (String args[]){

        evenThread a = new evenThread();
        oddThread b = new oddThread();

        Thread t1 =  new Thread (a);
        Thread t2 =  new Thread ( b);

        String msg = "ok";
        a.setAnother(msg);
        b.setAnother(msg);
        t1.start();
        t2.start();



    }
}


