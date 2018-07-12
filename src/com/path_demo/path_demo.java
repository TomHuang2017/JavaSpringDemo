package com.path_demo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import com.google.common.base.Preconditions;

public class path_demo {
    public static void main(String[] args)
    {
        String path=path_demo.class.getResource("").getPath();
        System.out.println(path);

        path=path_demo.class.getResource("/").getPath();
        System.out.println(path);

        Properties java_demo_properties=new Properties();

        //建议用这种方式获取properties
        URL properties_URL=Thread.currentThread().getContextClassLoader().getResource("java_demo.properties");
        Preconditions.checkNotNull(properties_URL);
        try {
            //Properties,从输入流中读取属性列表（建和元素对），加载后，可以进行getProperty和setProperty的操作，最后进行store,即保存进property.
            //默认继承至HashTable，
            java_demo_properties.load(properties_URL.openStream());
            //循环输出
            for (Map.Entry<Object,Object> item: java_demo_properties.entrySet()
                 ) {
                System.out.println(item.getKey()+":"+item.getValue());
            }

            //也可以获取指定的Property
            System.out.println(java_demo_properties.getProperty("name"));
            System.out.println(java_demo_properties.getProperty("age"));

            //添加属性
            java_demo_properties.setProperty("province","fj");

            //循环输出
            for (Map.Entry<Object,Object> item: java_demo_properties.entrySet()
                    ) {
                System.out.println(item.getKey()+":"+item.getValue());
            }

            //上面的添加属性只是放在内存，需要保存到Properties文件上
            OutputStream out=new FileOutputStream(properties_URL.getPath());
            java_demo_properties.store(out,"add location");
            //最终会保存到/E:/git_project/JavaSpringLearning/JavaSpringDemo/out/production/JavaSpringDemo/


        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(path);
    }
}
