package com.dateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
    private static Map<String,ThreadLocal<SimpleDateFormat>> map_sdf=new HashMap<String, ThreadLocal<SimpleDateFormat>>();
    public  final static String YYYYMMDD="yyyy-MM-dd";
    public  final static String YYYYMMDD_="yyyy-MM-dd";
    public  final static String YYYYMMDDHHmmss="yyyy-MM-dd HH:mm:ss";

    private static SimpleDateFormat getSDF(String pattern) {
        //1.尝试到map_sdf中获取当前pattern的ThreadLocal对象
        ThreadLocal<SimpleDateFormat> thread_sdf = map_sdf.get(pattern);
        //2.如果map中没有找到那个ThreadLocal对象，则实例化
        if (thread_sdf == null)
        {
            System.out.println(Thread.currentThread().getName()+"->thread_sdf with "+pattern+" is null, create a new one");
            //3.如果没有则创建
            thread_sdf=new ThreadLocal<SimpleDateFormat>();
            //4.创建后，往map_sdf中添加
            map_sdf.put(pattern,thread_sdf);
        }
        else
        {
            System.out.println(Thread.currentThread().getName()+"->thread_sdf is not null");
        }
        //5.找到后，尝试从thread_sdf中获取SimpleDateFormat对象，每个线程到这步第一次都会在她的ThreadLocalMap里存一个key
        //是thread_sdf, value是null的item， 第二次开始，就直接从当前子线程中取ThreadLocalMap里的这个key,value
        SimpleDateFormat sdf=thread_sdf.get();
        //6.如果找不到，则实例化
        if(sdf==null)
        {
            System.out.println(Thread.currentThread().getName()+"->sdf with "+pattern+" is null, create a new one");
            sdf=new SimpleDateFormat(pattern);
            //7.实例化后，添加到thread_sdf中,子线程里也会存一个key是thread_sdf,value是sdf的对象到他的ThreadLocalMap中，
            thread_sdf.set(sdf);
        }
        else
        {
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
