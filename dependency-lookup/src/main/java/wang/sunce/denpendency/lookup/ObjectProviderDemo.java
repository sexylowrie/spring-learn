package wang.sunce.denpendency.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import wang.sunce.ioc.domain.User;

public class ObjectProviderDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ObjectProviderDemo.class);
        context.refresh();
        lookupByObjectProvider(context);
        lookupIfAvailable(context);
        lookupByStream(context);
        context.close();

    }

    private static void lookupByStream(AnnotationConfigApplicationContext context) {
        ObjectProvider<String> beanProvider = context.getBeanProvider(String.class);
//        Iterable<String> iterable = beanProvider;
//        for(String str: iterable){
//            System.out.println(str);
//        }
        beanProvider.stream().forEach(System.out::println);
    }

    @Bean
    @Primary
    public String test() {
        return "test,sunce";
    }

    @Bean
    public String message() {
        return "hello world";
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext context) {
        ObjectProvider<User> beanProvider = context.getBeanProvider(User.class);
        User user = beanProvider.getIfAvailable(User::createUser);
        System.out.println(user);
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext context) {
        ObjectProvider<String> beanProvider = context.getBeanProvider(String.class);
        System.out.println(beanProvider.getObject());
    }
}
