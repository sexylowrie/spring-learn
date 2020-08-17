package wang.sunce.bean.instantiation.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct init doing");
    }

    @PreDestroy
    public void doDestroy() {
        System.out.println("@PreDestroy destroy doing");
    }

    public void initUserFactory() {
        System.out.println("@Bean init-method init doing");
    }


    public void destroyUserFactory() {
        System.out.println("@Bean destroy-method destroy doing");
    }

    @Override
    public void destroy() {
        System.out.println("DisposableBean destroy doing");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet init doing");
    }


    //不稳定出现
    @Override
    public void finalize() throws Throwable {
        System.out.println("UserFactory正在被回收...");
    }
}
