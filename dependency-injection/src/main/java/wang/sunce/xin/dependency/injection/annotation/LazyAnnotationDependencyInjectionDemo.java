package wang.sunce.xin.dependency.injection.annotation;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import wang.sunce.ioc.domain.User;

import java.util.Collection;
import java.util.Set;

/**
 * 基于注解的依赖注入实现
 */
public class LazyAnnotationDependencyInjectionDemo {


    @Autowired
    private User user;


    @Autowired
    private ObjectProvider<User> objectProvider;//延迟注入

    @Autowired
    private ObjectFactory<Set<User>> objectFactory;//延迟注入

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);
        applicationContext.refresh();

        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);

        System.out.println(demo.user);

        System.out.println(demo.objectProvider.getObject());

        System.out.println(demo.objectFactory.getObject());

        demo.objectProvider.forEach(System.out::println);

        applicationContext.close();
    }


}
