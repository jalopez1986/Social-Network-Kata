package com.jlopez.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        followees.put(followee.getUsername(),followee);
    }

    public List<User> getFollowees() {
        return new ArrayList<>(followees.values());
    }




}
