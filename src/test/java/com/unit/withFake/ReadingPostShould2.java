package com.unit.withFake;

import com.jlopez.actions.GetUserPosts;
import com.jlopez.domain.Post;
import com.jlopez.domain.Posts;
import com.jlopez.infrastructure.InMemoryPosts;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class ReadingPostShould2 {

    private static final String ANY_USERNAME = "anyUsername";
    private static final String FIRST_MESSAGE = "firstMessage";
    private static final String SECOND_MESSAGE = "secondMessage";

    private Post firstPost;
    private Post secondPost;

    Posts inMemoryPots;
    GetUserPosts getUserPosts;
    List<Post> userPosts;

    @Before
    public void setUp() throws Exception {
        inMemoryPots = new InMemoryPosts();
        getUserPosts = new GetUserPosts(inMemoryPots);
    }

    @Test
    public void a_user_can_view_the_posts_of_other_user() {
        given_a_user_with_2_posts();

        when_actions_is_executed();

        then_the_users_posts_are_listed();
    }

    @Test
    public void a_user_show_0_post_when_the_other_user_dont_have_post() {
        when_actions_is_executed();

        then_the_user_posts_are_zero();
    }


    private void given_a_user_with_2_posts() {
        firstPost = givenAPostWithThisMessage(FIRST_MESSAGE);
        secondPost = givenAPostWithThisMessage(SECOND_MESSAGE);

        inMemoryPots.addPost(ANY_USERNAME, firstPost);
        inMemoryPots.addPost(ANY_USERNAME, secondPost);

    }

    private Post givenAPostWithThisMessage(String message) {
        return new Post(message);
    }

    private void when_actions_is_executed() {
        userPosts = getUserPosts.execute(ANY_USERNAME);
    }

    private void then_the_users_posts_are_listed() {
        assertThat(userPosts.get(0)).isEqualTo(firstPost);
        assertThat(userPosts.get(1)).isEqualTo(secondPost);
    }

    private void then_the_user_posts_are_zero() {
        assertThat(userPosts.size()).isEqualTo(0);

    }

}
