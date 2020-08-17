package wang.sunce.bean.initalizing;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import wang.sunce.bean.instantiation.factory.DefaultUserFactory;
import wang.sunce.bean.instantiation.factory.UserFactory;
import wang.sunce.ioc.domain.User;

/**
 * Bean 初始化
 * 一：
 * 1.基于@PostConstruct
 * 2.实现接口InitializingBean接口
 * 3. 自定义实现：@Bean init-method
 */
public class BeanInitializationDemo {

    public static void main(String[] args) {

        //自定义1：通过@Bean注册实例化类
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanInitializationDemo.class);
        //自定义2：通过@Bean注册实例化类
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
//        beanDefinition.setBeanClass(DefaultUserFactory.class);
//        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
//        beanDefinition.setPropertyValues(mutablePropertyValues);
//        beanDefinition.setInitMethodName("initUserFactory");
//        context.registerBeanDefinition("userFactory", beanDefinition);
        // 自定义1自定义2 公用
        context.refresh();
        UserFactory bean = context.getBean(UserFactory.class);
        System.out.println(bean);
        context.close();
        //自定义3：通过XML注册实例化类
//        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-bean-initializing-context.xml");
//        UserFactory bean = beanFactory.getBean(UserFactory.class);

    }

    @Bean(initMethod = "initUserFactory")
    public DefaultUserFactory userFactory() {
        return new DefaultUserFactory();
    }


}
