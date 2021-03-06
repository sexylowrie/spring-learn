package wang.sunce.xin.dependency.injection.constructor;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import wang.sunce.ioc.domain.User;
import wang.sunce.xin.dependency.injection.UserHolder;

/**
 * 基于API的依赖注入实现
 * <p>
 * setter 调用顺序是不一定的，但是构造器是一定的
 */
public class ApiDependencyInjectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        BeanDefinition beanDefinition = createUserHolderBeanDefinition();
        applicationContext.registerBeanDefinition("userHolder", beanDefinition);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);
        applicationContext.refresh();
        UserHolder bean = applicationContext.getBean(UserHolder.class);
        User user = bean.getUser();
        System.out.println(user);
        applicationContext.close();
    }

    public static BeanDefinition createUserHolderBeanDefinition() {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        beanDefinitionBuilder.addConstructorArgReference("user");
        return beanDefinitionBuilder.getBeanDefinition();
    }


}
