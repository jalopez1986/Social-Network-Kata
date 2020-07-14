package com.acceptance;

import com.jlopez.actions.GetUserPosts;
import com.jlopez.domain.Post;
import com.jlopez.domain.Posts;
import com.jlopez.infrastructure.InMemoryPosts;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ReadingPostShould2 {

    public static final String ANY_USERNAME = "anyUsername";
    public static final String FIRST_MESSAGE = "firstMessage";
    public static final String SECOND_MESSAGE = "secondMessage";

    Posts inMemoryRepository;


    @Test
    public void a_user_can_view_the_posts_of_other_user() {
        inMemoryRepository = given_a_posts_repository_with_a_user_with_2_posts();
        GetUserPosts getUserPosts = new GetUserPosts(inMemoryRepository);

        List<Post> userPosts = getUserPosts.execute(ANY_USERNAME);

        assertThat(userPosts.get(0).getMessage()).isEqualTo(FIRST_MESSAGE);
        assertThat(userPosts.get(1).getMessage()).isEqualTo(SECOND_MESSAGE);
    }

    private Posts given_a_posts_repository_with_a_user_with_2_posts() {
        inMemoryRepository = new InMemoryPosts();
        inMemoryRepository.addPost(ANY_USERNAME, FIRST_MESSAGE);
        inMemoryRepository.addPost(ANY_USERNAME, SECOND_MESSAGE);

        return  inMemoryRepository;
    }
}
