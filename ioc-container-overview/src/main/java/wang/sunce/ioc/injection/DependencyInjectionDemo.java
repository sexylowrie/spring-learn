package wang.sunce.ioc.injection;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import wang.sunce.ioc.domain.User;
import wang.sunce.ioc.repository.UserRepository;

import java.util.Collection;

/**
 * 第二章
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        //实时查找
        BeanFactory factory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        //自定义Bean
        UserRepository userRepository = (UserRepository) factory.getBean("userRepository");
        Collection<User> users = userRepository.getUsers();
        BeanFactory beanFactory = userRepository.getBeanFactory();
        System.out.println(users);
        System.out.println(beanFactory == factory);
        //BeanFactory 不是一个普通的bean ，所以以上是false；容器内建依赖，非Bean
//        System.out.println(factory.getBean(BeanFactory.class));
//        ObjectFactory<User> objectFactory = userRepository.getObjectFactory();
        ObjectFactory<ApplicationContext> objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject());
        System.out.println(objectFactory.getObject() == factory);
        // 容器内建Bean对象
        Environment env = factory.getBean(Environment.class);
        System.out.println("获取容器内建Bean对象" + env);
    }

    //ClassPathXmlApplicationContext<- AbstractRefreshableApplicationContext <-AbstractApplicationContext#getBeanFactory
    //  <- ApplicationContext <- BeanFactor
    private void whoIsIoCContainer(UserRepository userRepository, BeanFactory factory) {
        ObjectFactory<ApplicationContext> objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject());
        System.out.println(objectFactory.getObject() == factory);
    }

    private void whoIsIoCContainer(UserRepository userRepository, ApplicationContext applicationContext) {
        ObjectFactory<ApplicationContext> objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject());
        System.out.println(objectFactory.getObject() == applicationContext);
    }


}
