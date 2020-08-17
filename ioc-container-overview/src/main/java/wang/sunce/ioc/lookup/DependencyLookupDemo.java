package wang.sunce.ioc.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import wang.sunce.ioc.annotation.Super;
import wang.sunce.ioc.domain.User;

import java.util.Map;
/**
 * 第一章
 */
public class DependencyLookupDemo {

    public static void main(String[] args) {
        //实时查找
        BeanFactory factory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");
        lookupByType(factory);
        lookupByCollectionType(factory);
        lookupRealTime(factory);
        lookupLazyTime(factory);
        lookupByAnnotation(factory);
    }

    private static void lookupByAnnotation(BeanFactory factory) {
        if (factory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) factory;
            Map<String, User> users = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("查找集合：" + users);
        }
    }


    private static void lookupByCollectionType(BeanFactory factory) {
        if (factory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) factory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找集合：" + users);
        }
    }

    /**
     * 按照类型查找
     * 3.0支持，3.0才支持范型
     *
     * @param factory
     */
    private static void lookupByType(BeanFactory factory) {
        User user = factory.getBean(User.class);
        System.out.println("类型查找：" + user);
    }

    private static void lookupRealTime(BeanFactory factory) {
        User user = (User) factory.getBean("user");
        System.out.println("实时查找：" + user);
    }

    private static void lookupLazyTime(BeanFactory factory) {
        ObjectFactory objectFactory = (ObjectFactory) factory.getBean("objectFactory");
        User user = (User) objectFactory.getObject();
        System.out.println("延迟查找：" + user);
    }
}
