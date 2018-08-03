package com.threadlocal;

import java.util.Date;

public class threadlocal_demo {
    /*
    static ThreadLocal<Long> integerLocal=new ThreadLocal<Long>();
    static ThreadLocal<Long> integerLocal2=new ThreadLocal<Long>();
    static ThreadLocal<String> stringLocal=new ThreadLocal<String>();
*/
    static ThreadLocal<Date> dateLocal=new ThreadLocal<Date>();

    public void set()
    {
        /*
        integerLocal.set((long) Thread.currentThread().getStackTrace().length);
        integerLocal2.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
*/
        Date d=dateLocal.get();
        if(d==null)
        {
            if(Thread.currentThread().getId()==1)
            {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            d=new Date();
            dateLocal.set(d);
        }
    }

    public void get()
    {/*
        System.out.println("Priority:"+ integerLocal.get());

        System.out.println("Id:"+ integerLocal2.get());
        System.out.println("Name:"+ stringLocal.get());*/
        System.out.println("Date:"+dateLocal.get().toString());

    }
    public static void main(String[] args)
    {


        threadlocal_demo demo=new threadlocal_demo();
        demo.set();
        demo.get();



    }
}
