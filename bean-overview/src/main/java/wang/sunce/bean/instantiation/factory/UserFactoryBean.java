package wang.sunce.bean.instantiation.factory;

import org.springframework.beans.factory.FactoryBean;
import wang.sunce.ioc.domain.User;

public class UserFactoryBean implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
