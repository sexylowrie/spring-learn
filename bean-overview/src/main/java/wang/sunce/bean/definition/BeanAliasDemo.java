package wang.sunce.bean.definition;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import wang.sunce.ioc.domain.User;

public class BeanAliasDemo {

    public static void main(String[] args) {
        BeanFactory factory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-bean-definition-context.xml");
        User user = factory.getBean("user", User.class);
        User sunce = factory.getBean("sunce-user", User.class);
        System.out.println(sunce == user);
    }


}
