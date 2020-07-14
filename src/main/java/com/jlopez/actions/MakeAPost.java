package com.jlopez.actions;

import com.jlopez.domain.Posts;

public class MakeAPost {


    private Posts posts;

    public MakeAPost(Posts posts) {

        this.posts = posts;
    }

    public void execute(String username, String message) {
        posts.addPost(username, message);
    }
}
