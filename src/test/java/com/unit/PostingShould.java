package com.unit;

import com.jlopez.actions.MakeAPost;
import com.jlopez.domain.Post;
import com.jlopez.domain.Posts;
import com.jlopez.domain.User;
import com.jlopez.domain.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostingShould {

    public static final String ANY_USERNAME = "anyUser";
    public static final String USERNAME_TO_LINK = "userToLink";
    public static final String ANY_MESSAGE = "message";
    public static final String ANY_MESSAGE_WITH_LINKED_USER = ANY_MESSAGE + " @" + USERNAME_TO_LINK;
    private static final String NON_EXISTING_USER = "non_existing";
    public static final String ANY_MESSAGE_WITH_A_NON_EXISTING_LINKED_USER = ANY_MESSAGE + " @" + NON_EXISTING_USER;


    @Mock Posts posts;
    @Mock Users users;
    @Captor ArgumentCaptor<Post> argument;

    private MakeAPost makeAPost;
    private User linkedUser;


    @Test
    public void a_user_can_publish_messages_to_a_personal_timeline() {
        given_a_makeAPost_action();

        when_the_action_is_executed_with(ANY_USERNAME, ANY_MESSAGE);

        then_the_post_is_saved();
    }

    @Test
    public void a_user_can_link_another_user_in_a_message_using_arroba() {
        given_a_makeAPost_action();
        and_given_two_existing_username();

        when_the_action_is_executed_with(ANY_USERNAME, ANY_MESSAGE_WITH_LINKED_USER);

        then_the_post_is_saved_with_the_linked_user();

    }

    @Test
    public void when_a_user_link_a_non_existing_user_the_post_dont_save_any_linked_user() {
        given_a_makeAPost_action();
        and_given_an_existing_user_and_a_non_existing_user();

        when_the_action_is_executed_with(ANY_USERNAME, ANY_MESSAGE_WITH_A_NON_EXISTING_LINKED_USER);

        then_the_post_is_saved_without_the_linked_user();

    }




    private void given_a_makeAPost_action() {
        makeAPost = new MakeAPost(users, posts);
    }

    private void and_given_two_existing_username() {
        linkedUser = new User(USERNAME_TO_LINK);
        when(users.tryGet(ANY_USERNAME)).thenReturn(Optional.of(new User(ANY_USERNAME)));
        when(users.tryGet(USERNAME_TO_LINK)).thenReturn(Optional.of(linkedUser));
    }

    private void and_given_an_existing_user_and_a_non_existing_user() {
        when(users.tryGet(ANY_USERNAME)).thenReturn(Optional.of(new User(ANY_USERNAME)));
        when(users.tryGet(NON_EXISTING_USER)).thenReturn(Optional.ofNullable(null));
    }

    private void when_the_action_is_executed_with(String username, String message) {
        makeAPost.execute(username, message);
    }

    void then_the_post_is_saved() {
        verify(posts, times(1)).addPost(eq(ANY_USERNAME), argument.capture());
        assertThat(argument.getValue().getMessage()).isEqualTo(ANY_MESSAGE);
    }

    private void then_the_post_is_saved_with_the_linked_user() {
        verify(posts, times(1)).addPost(eq(ANY_USERNAME), argument.capture());

        assertThat(argument.getValue().getMessage()).isEqualTo(ANY_MESSAGE_WITH_LINKED_USER);
        assertThat(argument.getValue().getLinkedUser().get()).isEqualTo(linkedUser);
    }

    private void then_the_post_is_saved_without_the_linked_user() {
        verify(posts, times(1)).addPost(eq(ANY_USERNAME), argument.capture());

        assertThat(argument.getValue().getMessage()).isEqualTo(ANY_MESSAGE_WITH_A_NON_EXISTING_LINKED_USER);
        assertThat(argument.getValue().getLinkedUser().isPresent()).isFalse();
    }

}
