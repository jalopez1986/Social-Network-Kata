package com.jlopez.actions;

import com.jlopez.domain.Post;
import com.jlopez.domain.Posts;

import java.util.List;

public class GetUserPosts {
    private Posts posts;

    public GetUserPosts(Posts posts) {
        this.posts = posts;
    }

    public List<Post> execute(String username) {
        return posts.getAllByUsername(username);
    }
}
