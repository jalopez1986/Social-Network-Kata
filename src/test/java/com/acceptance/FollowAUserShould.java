package com.acceptance;

import com.jlopez.actions.FollowAUser;
import com.jlopez.domain.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FollowAUserShould {
    @Mock Users users;
    private static final String ANY_USERNAME = "anyUsername";
    private static final String USERNAME_TO_FOLLOW = "otherUsername";

    @Test
    public void a_user_can_follow_other_user() {
        FollowAUser followAUser = new FollowAUser(users);

        followAUser.execute(ANY_USERNAME, USERNAME_TO_FOLLOW);

        verify(users,times(1)).save(any());
    }
}
