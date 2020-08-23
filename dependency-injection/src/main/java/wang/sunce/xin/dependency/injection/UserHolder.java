package wang.sunce.xin.dependency.injection;

import wang.sunce.ioc.domain.User;

public class UserHolder {

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
