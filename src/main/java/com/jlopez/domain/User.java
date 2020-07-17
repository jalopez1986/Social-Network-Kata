package com.jlopez.domain;

import java.util.*;

public class User {
    private String username;
    private Map<String,User> followees = new HashMap<>();

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void follow(User followee) {
        if (this.equals(followee)) throw new CannotFollowYourself();
        if (this.isFollowing(followee.getUsername())) throw new UserAlreadyFollowed();

        followees.put(followee.getUsername(),followee);
    }

    private boolean isFollowing(String username) {
        return followees.containsKey(username);
    }

    public List<User> getFollowees() {
        return new ArrayList<>(followees.values());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    public class CannotFollowYourself extends RuntimeException {
    }

    public class UserAlreadyFollowed extends RuntimeException {
    }

    static public class UserDoesNotExist extends RuntimeException {
    }

}
