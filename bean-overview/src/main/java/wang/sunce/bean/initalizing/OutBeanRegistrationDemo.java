package wang.sunce.bean.initalizing;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import wang.sunce.bean.instantiation.factory.DefaultUserFactory;
import wang.sunce.bean.instantiation.factory.UserFactory;

/**
 * 容器外部单体Bean注册
 */
public class OutBeanRegistrationDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        UserFactory userFactory = new DefaultUserFactory();
        beanFactory.registerSingleton("userFactory", userFactory);
        // 自定义1自定义2 公用
        context.refresh();
        UserFactory bean = context.getBean("userFactory", UserFactory.class);
        System.out.println(bean == userFactory);
        context.close();

    }


}
