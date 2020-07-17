package com.unit;

import com.jlopez.actions.MakeAPost;
import com.jlopez.domain.Post;
import com.jlopez.domain.Posts;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PostingShould {

    public static final String ANY_USERNAME = "anyUser";
    public static final String ANY_MESSAGE = "message";

    @Mock Posts posts;
    @Captor ArgumentCaptor<Post> argument;

    private MakeAPost makeAPost;


    @Test
    public void a_user_can_publish_messages_to_a_personal_timeline() {
        given_a_makeAPost_action();

        when_the_action_is_executed_with(ANY_USERNAME, ANY_MESSAGE);

        then_the_post_is_saved();
    }

    private void given_a_makeAPost_action() {
        makeAPost = new MakeAPost(posts);
    }

    private void when_the_action_is_executed_with(String username, String message) {
        makeAPost.execute(username, message);
    }

    void then_the_post_is_saved() {
        verify(posts, times(1)).addPost(eq(ANY_USERNAME), argument.capture());
        assertThat(argument.getValue().getMessage()).isEqualTo(ANY_MESSAGE);
    }

}
