package com.acceptance;

import com.jlopez.actions.MakeAPost;
import com.jlopez.domain.Posts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PostingShould {

    public static final String ANY_USERNAME = "anyUser";
    public static final String ANY_MESSAGE = "message";

    @Mock Posts posts;
    private MakeAPost makeAPost;

    @Test
    public void a_user_can_publish_messages_to_a_personal_timeline() {
        makeAPost = new MakeAPost(posts);

        makeAPost.execute(ANY_USERNAME, ANY_MESSAGE);

        verify(posts, times(1)).addPost(ANY_USERNAME, ANY_MESSAGE);

    }

}
