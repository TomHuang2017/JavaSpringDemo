package com;

import com.springinaction.springdio.xml.Performer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ApplicationContext ctx=new ClassPathXmlApplicationContext("beans.xml");
        Performer pf=(Performer) ctx.getBean("kenny2");
        pf.perform();
        System.out.println("Hello wold");
    }
}
