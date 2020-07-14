package com.jlopez.infrastructure;

import com.jlopez.domain.Post;
import com.jlopez.domain.Posts;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class InMemoryPosts implements Posts {

    LinkedHashMap<String, List<Post>> posts;

    public InMemoryPosts() {
        posts = new LinkedHashMap<>();
    }

    @Override
    public void addPost(String username, String message) {
        List<Post> userPosts = new ArrayList<>();

        if (posts.containsKey(username)) {
            userPosts = posts.get(username);
        }

        Post post = new Post(message);
        userPosts.add(post);

        posts.put(username, userPosts);
    }

    @Override
    public List<Post> getAllByUsername(String username) {
        return posts.get(username);
    }
}
