package wang.sunce.bean.instantiation;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import wang.sunce.bean.instantiation.factory.DefaultUserFactory;
import wang.sunce.bean.instantiation.factory.UserFactory;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 特殊实现
 */
public class BeanInstantiationSpecialDemo {

    public static void main(String[] args) {
        /*方式一*/
//        BeanFactory factory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-bean-creation-special-context.xml");
//        ServiceLoader<UserFactory> serviceLoader = factory.getBean("userFactoryServiceLoader", ServiceLoader.class);
//        serviceLoaderDemo();
//        dispalyServiceLoader(serviceLoader);

        /*方式二*/
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-bean-creation-special-context.xml");
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
//        ServiceLoader<UserFactory> serviceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);
//        dispalyServiceLoader(serviceLoader);
        DefaultUserFactory userFactory = beanFactory.createBean(DefaultUserFactory.class);
        System.out.println(userFactory.createUser());
    }

    public static void serviceLoaderDemo() {
        ServiceLoader<UserFactory> load = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        dispalyServiceLoader(load);
    }

    public static void dispalyServiceLoader(ServiceLoader<UserFactory> serviceLoader) {
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            UserFactory userFactory = iterator.next();
            System.out.println(userFactory.createUser());
        }
    }
}

