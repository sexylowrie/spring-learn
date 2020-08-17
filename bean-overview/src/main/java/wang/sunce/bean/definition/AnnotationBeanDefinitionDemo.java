package wang.sunce.bean.definition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import wang.sunce.ioc.domain.User;

//3。通过@Import导入
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

//        context.register(Config.class);
        context.register(AnnotationBeanDefinitionDemo.class);
        //启动
        context.refresh();
        System.out.println(context.getBeansOfType(Config.class));
        System.out.println(context.getBeansOfType(User.class));
        //1。通过@Bean定义
        //2。通过@Component
        //3。通过@Import导入
        //关闭
        context.close();

    }


    //2。通过@Component
    @Component
    public static class Config {

        //1。通过@Bean定义
        @Bean(name = {"user", "sunce-user"})
        public User user() {
            User user = new User();
            user.setName("sunce");
            user.setAge(18);
            return user;
        }
    }
}
