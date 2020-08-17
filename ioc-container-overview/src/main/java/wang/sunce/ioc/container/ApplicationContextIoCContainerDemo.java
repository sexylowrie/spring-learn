package wang.sunce.ioc.container;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import wang.sunce.ioc.domain.User;

import java.util.Map;

/**
 * 第三章#2
 * 注解能力ApplicationContext 作为容器
 */
public class ApplicationContextIoCContainerDemo {

    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 当前类作为配置类
        applicationContext.register(ApplicationContextIoCContainerDemo.class);
        //需要refresh
        //创建BeanFactory - 初步初始化 - 内建bean对象，内建bean依赖，以及非Bean对象 - beanFactory扩展点 - bean扩展点
        // -国际化
        applicationContext.refresh();
        lookupByCollectionType(applicationContext);
        // 关闭应用上下文- 执行shutDown hook
        // 销毁对象 - 关闭BeanFactory
        applicationContext.close();
    }

    @Bean
    private User user() {
        User user = new User();
        user.setAge(11);
        user.setName("sunce");
        return user;
    }

    private static void lookupByCollectionType(BeanFactory factory) {
        if (factory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) factory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找集合：" + users);
        }
    }
}
