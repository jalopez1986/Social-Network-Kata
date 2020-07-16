package com.jlopez.actions;

import com.jlopez.domain.Post;
import com.jlopez.domain.Posts;
import javafx.geometry.Pos;

public class MakeAPost {


    private Posts posts;

    public MakeAPost(Posts posts) {

        this.posts = posts;
    }

    public void execute(String username, String message) {
        Post post = new Post(message);
        posts.addPost(username, post);
    }
}
