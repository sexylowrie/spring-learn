package wang.sunce.bean.instantiation.factory;

import wang.sunce.ioc.domain.User;

public interface UserFactory {

    default User createUser(){
        return User.createUser();
    }
}
