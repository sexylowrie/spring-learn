package wang.sunce.xin.dependency.injection.setter;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import wang.sunce.ioc.domain.User;
import wang.sunce.xin.dependency.injection.UserHolder;

/**
 * 基于API的依赖注入实现
 */
public class AutowiringByTypeDependencyInjectionDemo {

    public static void main(String[] args) {
        //创建BeanFactory容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //读取Xml bean定义配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        String location = "classpath:/META-INF/autowiring-dependency-setter-injection.xml";
        reader.loadBeanDefinitions(location);
        UserHolder bean = factory.getBean(UserHolder.class);
        User user = bean.getUser();
        System.out.println(user);
    }


}
