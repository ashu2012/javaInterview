package com.stocknap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;


/*

This program prints even and odd sequence using two thread using samaphore

 */

public class Multithreading {

    static Semaphore  lck  = new Semaphore(1,true);
    static volatile AtomicInteger currThd = new AtomicInteger(0);
    static  class evenThread implements  Runnable{

            Thread another;

            public void setAnother(Thread t){
                this.another =  t;
            }

        @Override
        public void run() {

            while(currThd.get() <= 10){

                if (currThd.get() % 2==0 ) {
                    try {

                        lck.acquire();
                        System.out.println("acquiring lock by even thread "+another.getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(currThd);
                    currThd.addAndGet(1);
                    System.out.println("value increment by even thd "+another.getName());
                    lck.release();
                    System.out.println("lck released  by even thd "+another.getName());

                }


            }


        }



    }

    static class oddThread implements  Runnable{

        Thread another;

        public void setAnother(Thread t){
            this.another =  t;
        }

        @Override
        public void run() {
            while(currThd.get() < 10){

                if (currThd.get() % 2!=0  ) {
                    try {

                        lck.acquire();
                        System.out.println("acquiring lock by odd thread "+another.getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(currThd);
                    currThd.addAndGet(1);
                    System.out.println("value increment by  odd thd "+another.getName());
                    lck.release();
                    System.out.println("lck released  by  odd  thd "+another.getName());

                }




            }



        }



    }
    public static void runFlow (String args[]){

        evenThread a = new evenThread();
        oddThread b = new oddThread();

        Thread t1 =  new Thread (a);
        Thread t2 =  new Thread ( b);
        a.setAnother(t2);
        b.setAnother(t1);
        t1.start();
        t2.start();



    }
}


