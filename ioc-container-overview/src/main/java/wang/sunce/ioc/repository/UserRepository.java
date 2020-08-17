package wang.sunce.ioc.repository;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import wang.sunce.ioc.domain.User;

import java.util.Collection;

public class UserRepository {

    private Collection<User> users;//定义Bean

    private BeanFactory beanFactory;// 内建非Bean对象

    //    private ObjectFactory<User> objectFactory;
    private ObjectFactory<ApplicationContext> objectFactory;

//    public ObjectFactory<User> getObjectFactory() {
//        return objectFactory;
//    }
//
//    public void setObjectFactory(ObjectFactory<User> objectFactory) {
//        this.objectFactory = objectFactory;
//    }


    public ObjectFactory<ApplicationContext> getObjectFactory() {
        return objectFactory;
    }

    public void setObjectFactory(ObjectFactory<ApplicationContext> objectFactory) {
        this.objectFactory = objectFactory;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
