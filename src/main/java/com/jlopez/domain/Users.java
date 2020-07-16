package com.jlopez.domain;

public interface Users {
    boolean exist(String username);

    void save(User user);

    User get(String username);
}
