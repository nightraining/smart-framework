package org.smart4j.sample.bean;

import org.smart4j.sample.entity.User;

public class UserBean {

    private User user;

    public UserBean(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
