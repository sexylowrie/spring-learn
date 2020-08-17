package wang.sunce.bean.instantiation;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import wang.sunce.ioc.domain.User;

/**
 * Bean 实例化
 * 常规实现
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        BeanFactory factory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-bean-creation-context.xml");
        User user1 = factory.getBean("static-method-user", User.class);
        User user2 = factory.getBean("instance-method-user", User.class);
        User user3 = factory.getBean("factory-bean-user", User.class);
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(user1 == user2);
        System.out.println(user2 == user3);
    }
}

