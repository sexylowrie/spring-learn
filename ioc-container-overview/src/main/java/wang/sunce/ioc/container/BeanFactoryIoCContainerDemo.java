package wang.sunce.ioc.container;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import wang.sunce.ioc.domain.User;

import java.util.Map;

/**
 * 第三章#1
 */
public class BeanFactoryIoCContainerDemo {

    public static void main(String[] args) {
        //创建BeanFactory容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //读取Xml bean定义配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        int beanDefinitionsCount = reader.loadBeanDefinitions(location);
        System.out.println("Bean 定义加载数量:" + beanDefinitionsCount);

        lookupByCollectionType(factory);

    }

    private static void lookupByCollectionType(BeanFactory factory) {
        if (factory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) factory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找集合：" + users);
        }
    }
}
