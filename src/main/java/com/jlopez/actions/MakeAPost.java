package com.jlopez.actions;

import com.jlopez.domain.Post;
import com.jlopez.domain.Posts;
import com.jlopez.domain.User;
import com.jlopez.domain.Users;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MakeAPost {


    private Users users;
    private Posts posts;

    public MakeAPost(Users users, Posts posts) {
        this.users = users;
        this.posts = posts;
    }

    public void execute(String username, String message) {

        Optional<User> linkedUser = tryToGetLinkedUser(message);

        Post post;
        if (linkedUser.isPresent()) {
            post = new Post(message, linkedUser.get());
        } else {
            post = new Post(message);
        }

        posts.addPost(username, post);
    }

    private Optional<User> tryToGetLinkedUser(String message) {
        Pattern regularExpresionPattern = Pattern.compile("(?<=@)\\w+");
        Matcher matcher = regularExpresionPattern.matcher(message);
        if (matcher.find()) {
            return users.tryGet(matcher.group());
        }

        return Optional.ofNullable(null);
    }
}
