package wang.sunce.bean.initalizing;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import wang.sunce.bean.instantiation.factory.DefaultUserFactory;
import wang.sunce.bean.instantiation.factory.UserFactory;

/**
 * Bean 初始化
 * 一：
 * 1.基于@PreDestory
 * 2.实现接口DisposeableBean接口
 * 3. 自定义实现：@Bean destroy-method
 */
public class BeanDestroyDemo {

    public static void main(String[] args) {

        //自定义1：通过@Bean注册实例化类
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanDestroyDemo.class);
        //自定义2：通过@Bean注册实例化类
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
//        beanDefinition.setBeanClass(DefaultUserFactory.class);
//        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
//        beanDefinition.setPropertyValues(mutablePropertyValues);
//        beanDefinition.setDestroyMethodName("destroyUserFactory");
//        context.registerBeanDefinition("userFactory", beanDefinition);
        // 自定义1自定义2 公用
        context.refresh();
        System.out.println("正在启动上下文");
        UserFactory bean = context.getBean(UserFactory.class);
        System.out.println(bean);
        System.out.println("准备销毁上下文");
        context.close();
        System.out.println("销毁上下文");
        //自定义3：通过XML注册实例化类
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-bean-destroy-context.xml");
//        UserFactory bean = context.getBean(UserFactory.class);
//        System.out.println(bean);
//        System.out.println("准备销毁上下文");
//        context.close();
//        System.out.println("销毁上下文");


    }

    @Bean(destroyMethod = "destroyUserFactory")
    public DefaultUserFactory userFactory() {
        return new DefaultUserFactory();
    }


}
