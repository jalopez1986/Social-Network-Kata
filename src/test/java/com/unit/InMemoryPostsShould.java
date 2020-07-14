package com.unit;

import com.jlopez.domain.Post;
import com.jlopez.infrastructure.InMemoryPosts;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryPostsShould {
    @Test
    public void save_the_post() {
        InMemoryPosts inMemoryPosts = new InMemoryPosts();
        String anyUser = "@anyUser";

        inMemoryPosts.addPost(anyUser, "message");

        List<Post> posts = inMemoryPosts.getAllByUsername(anyUser);

        assertThat(posts.size()).isEqualTo(1);
        assertThat(posts.get(0).getMessage()).isEqualTo("message");

    }
}
