package com.jlopez.domain;

import java.util.Optional;

public interface Users {
    void save(User user);

    Optional<User> tryGet(String username);
}
