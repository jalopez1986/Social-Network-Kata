package com.unit.withFake;

import com.jlopez.actions.GetUserPosts;
import com.jlopez.domain.Post;
import com.jlopez.domain.Posts;
import com.jlopez.infrastructure.InMemoryPosts;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class ReadingPostShould2 {

    private static final String ANY_USERNAME = "anyUsername";
    private static final String FIRST_MESSAGE = "firstMessage";
    private static final String SECOND_MESSAGE = "secondMessage";

    private Post firstPost;
    private Post secondPost;

    Posts inMemoryRepository;


    @Test
    public void a_user_can_view_the_posts_of_other_user() {
        inMemoryRepository = given_a_posts_repository_with_a_user_with_2_posts();
        GetUserPosts getUserPosts = new GetUserPosts(inMemoryRepository);

        List<Post> userPosts = getUserPosts.execute(ANY_USERNAME);

        assertThat(userPosts.get(0)).isEqualTo(firstPost);
        assertThat(userPosts.get(1)).isEqualTo(secondPost);
    }

    private Posts given_a_posts_repository_with_a_user_with_2_posts() {
        inMemoryRepository = new InMemoryPosts();
        firstPost = givenAPostWithThisMessage(FIRST_MESSAGE);
        secondPost = givenAPostWithThisMessage(SECOND_MESSAGE);

        inMemoryRepository.addPost(ANY_USERNAME, firstPost);
        inMemoryRepository.addPost(ANY_USERNAME, secondPost);

        return inMemoryRepository;
    }

    private Post givenAPostWithThisMessage(String message) {
        return new Post(message);
    }
}
