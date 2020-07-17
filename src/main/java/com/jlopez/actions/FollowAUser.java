package com.jlopez.actions;

import com.jlopez.domain.User;
import com.jlopez.domain.Users;

public class FollowAUser {

    private Users users;

    public FollowAUser(Users users) {
        this.users = users;
    }

    public void execute(String username, String usernameToFollow) {
        User user = users.tryGet(username).orElseThrow(User.UserDoesNotExist::new);
        User followee = users.tryGet(usernameToFollow).orElseThrow(User.UserDoesNotExist::new);

        user.follow(followee);
        users.save(user);
    }

}
