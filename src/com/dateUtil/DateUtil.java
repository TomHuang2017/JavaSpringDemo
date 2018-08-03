package com.dateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
    //全局静态变量，所有线程都只能访问一个
    private static Map<String,ThreadLocal<SimpleDateFormat>> map_sdf=new HashMap<String, ThreadLocal<SimpleDateFormat>>();
    public  final static String YYYYMMDD="yyyy-MM-dd";
    public  final static String YYYYMMDD_="yyyy-MM-dd";
    public  final static String YYYYMMDDHHmmss="yyyy-MM-dd HH:mm:ss";

    private static SimpleDateFormat getSDF(String pattern) {
        //1.尝试到map_sdf中获取当前pattern的ThreadLocal对象,该对象属于全局共享的对象，
        ThreadLocal<SimpleDateFormat> thread_sdf = map_sdf.get(pattern);
        //2.如果map中没有找到那个ThreadLocal对象，则实例化
        if (thread_sdf == null)
        {
            //8.由于map_sdf是所有线程都共享的，所以存在多个线程同时往map_sdf里插入key和value的情况。
            // 所以要求当所有线程在thread_sdf都未空时，都等待创建thread_sdf的那个线程。
            synchronized (DateUtil.class) {
                thread_sdf=map_sdf.get(pattern);
                if(thread_sdf==null) {
                    System.out.println(Thread.currentThread().getName() + "->thread_sdf with " + pattern + " is null, create a new one");
                    //3.如果没有则创建
                    thread_sdf = new ThreadLocal<SimpleDateFormat>();
                    //4.创建后，往map_sdf中添加
                    map_sdf.put(pattern, thread_sdf);
                }
            }
        }
        else
        {
            //如果找到，则这个thread_sdf是全局唯一的，所有线程共享
            System.out.println(Thread.currentThread().getName()+"->thread_sdf is not null");
        }
        //5.[这一步开始，每个当前线程都会访问各自的ThreadLocalMap对象]
        // 全局map_sdf里找到后，尝试从当前线程的ThreadLocalMap里找key是thread_sdf的value,即获取SimpleDateFormat对象。
        SimpleDateFormat sdf=thread_sdf.get();
        //6.如果找不到，即当前线程第一次使用该thread_sdf变量访问get方法，因为没有实例化，则实例化
        if(sdf==null)
        {
            //由于sdf每个线程都有一个对象，所以不需要加sync锁
            System.out.println(Thread.currentThread().getName()+"->sdf with "+pattern+" is null, create a new one");
            sdf=new SimpleDateFormat(pattern);
            //7.实例化后，添加到thread_sdf中,子线程里也会存一个key是thread_sdf,value是sdf的对象到他的ThreadLocalMap中，
            thread_sdf.set(sdf);
        }
        else
        {
            //如果找到，当前
            System.out.println(Thread.currentThread().getName()+"->sdf is not null");
        }
        //8.返回最终的SimpleDateFormat对象sdf
        return sdf;
    }

    private static  String dateToString(Date date, String pattern)
    {
        return getSDF(pattern).format(date);
    }
    private static Date stringToDate(String date,String pattern)
    {
        Date date_= null;
        try {
            date_ = getSDF(pattern).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date_;
    }

    public static void main(String [] args)
    {
        System.out.println(dateToString(new Date(),YYYYMMDD));
        System.out.println(dateToString(stringToDate("2017-02-01",YYYYMMDD),YYYYMMDDHHmmss));

        System.out.println("Starting test multiple theads...");
        new Thread(()->{
            DateUtil.dateToString(new Date(),YYYYMMDD);
        }).start();
        new Thread(()->{
            DateUtil.dateToString(new Date(),YYYYMMDD);
        }).start();
    }
}
