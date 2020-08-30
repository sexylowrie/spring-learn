package wang.sunce.xin.dependency.injection.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import wang.sunce.ioc.domain.User;
import wang.sunce.xin.dependency.injection.UserHolder;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * 基于注解的依赖注入实现
 */
public class QualifierAnnotationDependencyInjectionDemo {


    @Autowired
    private User user;

    @Autowired
    @Qualifier("user")//指定名称或者id
    private User namedUser;

    // 目前定义了4个user

    @Autowired
    private Collection<User> users;

    //两个找出qualifier User
    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers;

    @Autowired
    @UserGroup
    private Collection<User> groupUsers;

    @Bean
    @Qualifier
    public User user1() {
        User user = new User();
        user.setAge(14);
        return user;
    }

    @Bean
    @Qualifier
    public User user2() {
        User user = new User();
        user.setAge(15);
        return user;
    }

    @Bean
    @UserGroup
    public User user3() {
        User user = new User();
        user.setAge(16);
        return user;
    }

    @Bean
    @UserGroup
    public User user4() {
        User user = new User();
        user.setAge(17);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);
        applicationContext.refresh();

        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

        System.out.println(demo.user);

        System.out.println(demo.namedUser);

        System.out.println(demo.users);

        System.out.println(demo.qualifierUsers);

        System.out.println(demo.groupUsers);
        applicationContext.close();
    }


}
