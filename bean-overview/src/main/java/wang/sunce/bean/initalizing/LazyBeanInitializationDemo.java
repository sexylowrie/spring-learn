package wang.sunce.bean.initalizing;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import wang.sunce.bean.instantiation.factory.DefaultUserFactory;
import wang.sunce.bean.instantiation.factory.UserFactory;

/**
 * Bean 延迟初始化
 * 1.基于@Lazy注解
 * 2.XML lazy-init
 * 非Lazy是上下文启动过程中就完成初始化，lazy是上下文启动后，按需要初始化
 */
public class LazyBeanInitializationDemo {

    public static void main(String[] args) {

        //1：通过@Lazy
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(LazyBeanInitializationDemo.class);
        context.refresh();
        System.out.println("spring 上下文已启动");
        UserFactory bean = context.getBean(UserFactory.class);
        System.out.println(bean);
        context.close();
        //2：通过XML注册实例化类
//        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-bean-lazy-initializing-context.xml");
//        System.out.println("spring 上下文已启动");
//        UserFactory bean = beanFactory.getBean(UserFactory.class);

    }

//    @Lazy(value = false)
    @Lazy
    @Bean(initMethod = "initUserFactory")
    public DefaultUserFactory userFactory() {
        return new DefaultUserFactory();
    }


}
