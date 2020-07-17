package com.jlopez.domain;

import java.util.Objects;
import java.util.Optional;

public class Post {
    private String message;
    private User linkedUser;

    public Post(String message) {
        this.message = message;
    }

    public Post(String message, User user) {
        this.message = message;
        linkedUser = user;
    }

    public String getMessage() {
        return message;
    }

    public Optional<User> getLinkedUser() {
        return Optional.ofNullable(linkedUser);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(message, post.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }
}
