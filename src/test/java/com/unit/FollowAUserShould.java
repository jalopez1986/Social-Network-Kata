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

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FollowAUserShould {
    @Mock Users users;
    @Captor ArgumentCaptor<User> argument;

    private static final String ANY_USERNAME = "anyUsername";
    private static final String USERNAME_TO_FOLLOW = "otherUsername";

    @Test
    public void a_user_can_follow_other_user() {
        FollowAUser followAUser = new FollowAUser(users);
        when(users.get(ANY_USERNAME)).thenReturn(new User(ANY_USERNAME));
        when(users.get(USERNAME_TO_FOLLOW)).thenReturn(new User(USERNAME_TO_FOLLOW));

        followAUser.execute(ANY_USERNAME, USERNAME_TO_FOLLOW);

        verify(users,times(1)).save(argument.capture());
        List<User> followees = argument.getValue().getFollowees();
        assertThat(followees.size()).isEqualTo(1);
        assertThat(followees.get(0).getUsername()).isEqualTo(USERNAME_TO_FOLLOW);
    }
}
