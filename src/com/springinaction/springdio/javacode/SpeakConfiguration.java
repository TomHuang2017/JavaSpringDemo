package com.springinaction.springdio.javacode;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpeakConfiguration {
    //该方法等价于在xml配置中添加一个<bean>元素
    //@Bean告诉spring,这个方法将返回一个SpeakHello的对象，对象的id就是方法名
    //和xml相比，优点是xml里是字符串，没法在编译器进行检查，但是Java Code可以。

    @Bean
    public SpeakHello speak_hello() {
        return new SpeakHello("this is a new name value passed to the class");
    }

    @Bean
    public Spoker tom_spoker()
    {
        Spoker sp=new Spoker();
        sp.setSpeak(speak_hello());
        //上面的speak_hello() 不是实例化一个新的类，Spring发现某个消费者bean类调用该方法时
        //会拦截该方法，并尝试在应用上下文中查找该bean,即speak_hello.而不是创建一个新的类实例
        return sp;
    }
}
