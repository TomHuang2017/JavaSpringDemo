package com.thread_sync;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class thread_sync {

    private final static SimpleDateFormat sdf_yhm=new SimpleDateFormat("yyyy-MM-dd");

    /*时间类型转为一定格式的字符串*/
    public static  String formatDate(long date,String formatString)
    {
        return (new SimpleDateFormat(formatString)).format(date);
    }

    /*字符类型转为一定格式的时间,为了显示效果，又把最终的Date转为String来显示*/
    public static String stringToDate (String stringDateValue, String return_date_format_string)
    {
        Date date=new Date();
        try {
             date=(new SimpleDateFormat(return_date_format_string)).parse(stringDateValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatDate(date.getTime(),return_date_format_string);
    }

    public static void main(String [] args )
    {
        System.out.println(formatDate(System.currentTimeMillis(),"yyyy-MM-dd"));

        System.out.println(stringToDate("2017-01-01 12:20:30","yyyy-MM-dd"));
    }
}
