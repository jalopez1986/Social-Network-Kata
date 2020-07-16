package com.jlopez.infrastructure;

import com.jlopez.domain.Post;
import com.jlopez.domain.Posts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class InMemoryPosts implements Posts {

    LinkedHashMap<String, List<Post>> posts;

    public InMemoryPosts() {
        posts = new LinkedHashMap<>();
    }

    @Override
    public void addPost(String username, Post post) {
        List<Post> userPosts = new ArrayList<>();

        if (posts.containsKey(username)) {
            userPosts = posts.get(username);
        }

        userPosts.add(post);

        posts.put(username, userPosts);
    }


    @Override
    public List<Post> getAllByUsername(String username) {
        if (posts.get(username) == null) {
            return Collections.emptyList();
        }

        return posts.get(username);
    }
}
