package wang.sunce.xin.dependency.injection.filed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import wang.sunce.ioc.domain.User;
import wang.sunce.xin.dependency.injection.UserHolder;

import javax.annotation.Resource;

/**
 * 基于注解的依赖注入实现
 */
public class AnnotationDependencyInjectionDemo {

    @Autowired
    private UserHolder userHolder;
    // @Autowired 不会处理静态字段，只会处理实例字段
    //    static UserHolder userHolder;

    @Resource
    private UserHolder userHolder2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyInjectionDemo.class);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);
        applicationContext.refresh();

        AnnotationDependencyInjectionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionDemo.class);
        UserHolder userHolder = demo.userHolder;
        System.out.println(userHolder);
        UserHolder userHolder2 = demo.userHolder2;
        System.out.println(userHolder2);
        System.out.println(userHolder == userHolder2);
        applicationContext.close();
    }


    @Bean()
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
