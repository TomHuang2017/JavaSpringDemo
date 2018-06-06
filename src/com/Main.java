package com;

import com.springinaction.springdio.aspectJ.studentBLL;
import com.springinaction.springdio.autowried.Performer;
import com.springinaction.springdio.compont.Say;
import com.springinaction.springdio.javacode.Spoker;
import com.springinaction.springdio.aop_xml.insertRecordIntoDB;
import com.springinaction.springdio.xml.Instrument;
import com.springinaction.springdio.xml.Instrumentalist;
import org.springframework.cglib.core.Converter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

enum inactionType{
    aop_xml,
    autowried,
    compont,
    javacode,
    xml,
    aspectJ,
    aspectJ_xml_WithAspectJNode
}
public class Main {

    public static void main(String[] args) {

        callInterfaceFunction(inactionType.aspectJ_xml_WithAspectJNode);
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
                s.recordInsert();
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
            case aspectJ:
                ApplicationContext appCtx = new ClassPathXmlApplicationContext("com/springinaction/springdio/aspectJ/beans.xml");
                com.springinaction.springdio.aspectJ.insertRecordIntoDB student = (com.springinaction.springdio.aspectJ.insertRecordIntoDB)appCtx.getBean("student");
                student.recordInsert(12,"tom");
                break;
            case aspectJ_xml_WithAspectJNode:
                ApplicationContext appCtx_withAspectJNode = new ClassPathXmlApplicationContext("com/springinaction/springdio/aop_xml_withAopNode/beans.xml");
                com.springinaction.springdio.aop_xml_withAopNode.insertRecordIntoDB studentx = (com.springinaction.springdio.aop_xml_withAopNode.insertRecordIntoDB)appCtx_withAspectJNode.getBean("student");
                studentx.recordInsert(300,"tom and Joson");
                break;
            default:
                break;
        }
    }
}
