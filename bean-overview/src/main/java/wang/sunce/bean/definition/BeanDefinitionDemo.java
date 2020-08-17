package wang.sunce.bean.definition;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import wang.sunce.ioc.domain.User;

@Import(BeanDefinitionDemo.Config.class)
public class BeanDefinitionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(BeanDefinitionDemo.class);
        //1.命名方式注册
        registerUserBeanDefinition(context, "lowrie");
        //2.非命名方式注册
        registerUserBeanDefinition(context);
        //启动
        context.refresh();
        System.out.println(context.getBeansOfType(User.class));
        //关闭
        context.close();

    }

    /**
     * @param registry
     * @param name
     */
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String name) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        builder.addPropertyValue("name", "sunce")
                .addPropertyValue("age", 18);
        if (StringUtils.hasText(name)) {
            registry.registerBeanDefinition(name, builder.getBeanDefinition());
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(builder.getBeanDefinition(), registry);
        }

    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }


    @Component
    public static class Config {

        @Bean(name = {"user", "sunce-user"})
        public User user() {
            User user = new User();
            user.setName("sunce");
            user.setAge(18);
            return user;
        }
    }
}
