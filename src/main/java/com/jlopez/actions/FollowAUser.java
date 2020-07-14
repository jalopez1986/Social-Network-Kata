package com.jlopez.actions;

import com.jlopez.domain.User;
import com.jlopez.domain.Users;

public class FollowAUser {

    private Users users;

    public FollowAUser(Users users) {
        this.users = users;
    }

    public void execute(String username, String usernameToFollow) {
        users.save(new User());

    }
}
