package com.unit;

import com.jlopez.actions.FollowAUser;
import com.jlopez.domain.User;
import com.jlopez.domain.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FollowAUserShould {
    @Mock Users users;
    @Captor ArgumentCaptor<User> argument;

    private static final String ANY_USERNAME = "anyUsername";
    private static final String USERNAME_TO_FOLLOW = "otherUsername";

    FollowAUser followAUser;

    @Test
    public void a_user_can_follow_other_user() {
        given_a_followUser_action();
        and_given_two_different_users_when_get_users_from_the_repository();

        when_action_follow_action_is_executed_with(ANY_USERNAME, USERNAME_TO_FOLLOW);

        then_the_first_user_follow_the_other_user();
    }

    @Test
    public void an_user_cannot_follows_himself() {
        given_a_followUser_action();
        and_given_the_same_user_when_get_users_from_the_repository();

        assertThatThrownBy(() -> when_action_follow_action_is_executed_with(ANY_USERNAME, ANY_USERNAME))
                .isInstanceOf(User.CannotFollowYourself.class);
    }

    @Test
    public void an_user_cannot_follow_an_already_followed_user() {
        given_a_followUser_action();
        and_given_a_any_user_that_follow_another_user();

        assertThatThrownBy(() -> when_action_follow_action_is_executed_with(ANY_USERNAME, USERNAME_TO_FOLLOW))
                .isInstanceOf(User.UserAlreadyFollowed.class);
    }


    private void given_a_followUser_action() {
        followAUser = new FollowAUser(users);

    }

    private void and_given_two_different_users_when_get_users_from_the_repository() {
        when(users.get(ANY_USERNAME)).thenReturn(new User(ANY_USERNAME));
        when(users.get(USERNAME_TO_FOLLOW)).thenReturn(new User(USERNAME_TO_FOLLOW));
    }

    private void and_given_the_same_user_when_get_users_from_the_repository() {
        when(users.get(ANY_USERNAME)).thenReturn(new User(ANY_USERNAME));
    }

    private void and_given_a_any_user_that_follow_another_user() {
        User anyUser = new User(ANY_USERNAME);
        User userToFollow = new User(USERNAME_TO_FOLLOW);
        anyUser.follow(userToFollow);

        when(users.get(ANY_USERNAME)).thenReturn(anyUser);
        when(users.get(USERNAME_TO_FOLLOW)).thenReturn(userToFollow);

    }

    private void when_action_follow_action_is_executed_with(String anyUsername, String usernameToFollow) {
        followAUser.execute(anyUsername, usernameToFollow);
    }


    private void then_the_first_user_follow_the_other_user() {
        verify(users,times(1)).save(argument.capture());
        List<User> followees = argument.getValue().getFollowees();
        assertThat(followees.size()).isEqualTo(1);
        assertThat(followees.get(0).getUsername()).isEqualTo(USERNAME_TO_FOLLOW);
    }
}
