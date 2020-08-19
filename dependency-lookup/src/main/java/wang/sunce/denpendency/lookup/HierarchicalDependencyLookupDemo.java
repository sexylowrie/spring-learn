package wang.sunce.denpendency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HierarchicalDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(HierarchicalDependencyLookupDemo.class);

        //1.获取HierarchicalBeanFactory -》ConfigurableBeanFactory -》ConfigurableListableBeanFactory
        context.refresh();
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        System.out.println("beanFactory 的parentBeanFactory:" + beanFactory.getParentBeanFactory());
        //2.设置 Parent BeanFactory
        HierarchicalBeanFactory parent = createBeanFactory();
        beanFactory.setParentBeanFactory(parent);
        System.out.println("beanFactory 的parentBeanFactory:" + beanFactory.getParentBeanFactory());
        displayContainsLocalBean(beanFactory, "user");
        displayContainsLocalBean(parent, "user");
        displayContainsBean(beanFactory, "user");
        context.close();

    }

    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String name) {
        System.out.println("displayContainsBean当前是否包含：" + containsBean(beanFactory, name));
    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String name) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory cast = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if (containsBean(cast, name)) {
                return true;
            }
        }
        return beanFactory.containsLocalBean(name);
    }

    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String name) {
        System.out.println("displayContainsLocalBean当前是否包含：" + beanFactory.containsLocalBean(name));
    }

    private static ConfigurableListableBeanFactory createBeanFactory() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //读取Xml bean定义配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        int beanDefinitionsCount = reader.loadBeanDefinitions(location);
        System.out.println("Bean 定义加载数量:" + beanDefinitionsCount);
        return factory;
    }


}
