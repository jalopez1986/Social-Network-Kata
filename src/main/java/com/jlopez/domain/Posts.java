package com.jlopez.domain;

import java.util.List;

public interface Posts {
    void addPost(String username, String message);

    public List<Post> getAllByUsername(String username);
}
