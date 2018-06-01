package com;

import com.springinaction.springdio.autowried.Performer;
import com.springinaction.springdio.compont.Say;
import com.springinaction.springdio.javacode.Spoker;
import com.springinaction.springdio.aop_xml.insertRecordIntoDB;
import com.springinaction.springdio.xml.Instrument;
import com.springinaction.springdio.xml.Instrumentalist;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

enum inactionType{
    aop_xml,
    autowried,
    compont,
    javacode,
    xml
}
public class Main {

    public static void main(String[] args) {

        callInterfaceFunction(inactionType.aop_xml);
    }

    public static void callInterfaceFunction(inactionType type) {
        switch (type) {
            case autowried:
                ApplicationContext ctx = new ClassPathXmlApplicationContext("com/springinaction/springdio/autowried/beans.xml");
                Performer pf = (Performer) ctx.getBean("kenny2");
                pf.perform();
                System.out.println("Hello wold");
                break;
            case aop_xml:
                ApplicationContext ctx_aop_xml =
                        new ClassPathXmlApplicationContext("com/springinaction/springdio/aop_xml/beans.xml");
                insertRecordIntoDB s = (insertRecordIntoDB) ctx_aop_xml.getBean("studentProxy");
                s.insertRecord();
                break;
            case compont:
                ApplicationContext ctx_compont = new ClassPathXmlApplicationContext("com/springinaction/springdio/compont/beans.xml");
                Say say = (Say) ctx_compont.getBean("my_speaker");
                say.SayWord();
                break;
            case javacode:
                ApplicationContext ctx_javacode =
                        new AnnotationConfigApplicationContext(com.springinaction.springdio.javacode.SpeakConfiguration.class);
                Spoker sp = (Spoker) ctx_javacode.getBean("tom_spoker");
                sp.SayWord();
                break;
            case xml:
                ApplicationContext ctx_xml = new ClassPathXmlApplicationContext("com/springinaction/springdio/xml/beans.xml");
                Instrumentalist pf_xml = (Instrumentalist) ctx_xml.getBean("kenny2");
                pf_xml.perform();
                break;
            default:
                break;
        }
    }
}
