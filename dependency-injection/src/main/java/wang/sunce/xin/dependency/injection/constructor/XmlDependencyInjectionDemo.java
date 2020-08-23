package wang.sunce.xin.dependency.injection.constructor;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import wang.sunce.ioc.domain.User;
import wang.sunce.xin.dependency.injection.UserHolder;

/**
 * 基于XML的依赖注入实现
 */
public class XmlDependencyInjectionDemo {

    public static void main(String[] args) {
        //创建BeanFactory容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //读取Xml bean定义配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        String location = "classpath:/META-INF/dependency-constructor-injection.xml";
        reader.loadBeanDefinitions(location);
        UserHolder bean = factory.getBean(UserHolder.class);
        User user = bean.getUser();
        System.out.println(user);

    }
}
