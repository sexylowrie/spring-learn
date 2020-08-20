package wang.sunce.denpendency.lookup;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import wang.sunce.ioc.domain.User;

public class SafetyDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SafetyDependencyLookupDemo.class);
        context.refresh();
        displayBeanFactoryGetBean(context);
        displayObjectFactoryGetBean(context);
        displayObjectProviderGetIfAvailable(context);
        displayListableBeanFactoryGetBeansOfType(context);
        displayObjectProviderStreamcontext(context);
        context.close();
    }

    /**
     * ObjectProvider#stream:安全性是
     * 前提是获取的类是非抽象类，或者接口类
     *
     * @param factory
     */
    private static void displayObjectProviderStreamcontext(AnnotationConfigApplicationContext factory) {
        ObjectProvider<User> beanProvider = factory.getBeanProvider(User.class);
        printBeansException("displayListableBeanFactoryGetBeansOfType", () ->
                beanProvider.forEach(System.out::println)
        );
    }

    /**
     * ListableBeanFactory#getBeansOfType:安全性是
     * 前提是获取的类是非抽象类，或者接口类
     *
     * @param factory
     */
    private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory factory) {
        printBeansException("displayListableBeanFactoryGetBeansOfType", () ->
                factory.getBeansOfType(User.class)
        );
    }

    /**
     * ObjectProvider#getIfAvailable：安全性是
     *
     * @param factory
     */
    private static void displayObjectProviderGetIfAvailable(AnnotationConfigApplicationContext factory) {
        ObjectProvider<User> beanProvider = factory.getBeanProvider(User.class);
        printBeansException("displayObjectProviderGetIfAvailable", () ->
                beanProvider.getIfAvailable()
        );
    }

    /**
     * ObjectFactory#getObject：安全性否
     *
     * @param factory
     */
    private static void displayObjectFactoryGetBean(AnnotationConfigApplicationContext factory) {

        ObjectProvider<User> beanProvider = factory.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetBean", () ->
                beanProvider.getObject()
        );

    }

    /**
     * BeanFactory@getBean：安全性否
     *
     * @param factory
     */
    public static void displayBeanFactoryGetBean(BeanFactory factory) {
        printBeansException("displayBeanFactoryGetBean", () ->
                factory.getBean(User.class)
        );
    }

    private static void printBeansException(String msg, Runnable runnable) {
        System.err.println("============================");
        System.err.println("source from:" + msg);
        try {
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }

    }
}
