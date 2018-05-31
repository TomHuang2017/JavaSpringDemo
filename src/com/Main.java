package com;

import com.springinaction.springdio.autowried.Performer;
import com.springinaction.springdio.compont.Say;
import com.springinaction.springdio.javacode.Spoker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
         /*autowried code
        ApplicationContext ctx = new ClassPathXmlApplicationContext("com/springinaction/springdio/autowried/beans.xml");
        Performer pf = (Performer) ctx.getBean("kenny2");
        pf.perform();
        System.out.println("Hello wold");
        */

/*      compont
        ApplicationContext ctx=new ClassPathXmlApplicationContext("com/springinaction/springdio/compont/beans.xml");
        Say s=(Say)ctx.getBean("my_speaker");
        s.SayWord();
*/

        //JavaCode
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(com.springinaction.springdio.javacode.SpeakConfiguration.class);
        Spoker sp = (Spoker) ctx.getBean("tom_spoker");
        sp.SayWord();

    }
}
