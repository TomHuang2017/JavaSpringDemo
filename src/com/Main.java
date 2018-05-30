package com;

import com.springinaction.springdio.autowried.Performer;
import com.springinaction.springdio.compont.Say;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        // write your code here
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("com/springinaction/springdio/autowried/beans.xml");
//        Performer pf = (Performer) ctx.getBean("kenny2");
//        pf.perform();
//        System.out.println("Hello wold");

        ApplicationContext ctx=new ClassPathXmlApplicationContext("com/springinaction/springdio/compont/beans.xml");
        Say s=(Say)ctx.getBean("my_speaker");
        s.SayWord();
    }
}
