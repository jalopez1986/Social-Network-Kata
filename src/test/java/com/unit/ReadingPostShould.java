package com.unit;

import com.jlopez.actions.GetUserPosts;
import com.jlopez.domain.Post;
import com.jlopez.domain.Posts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReadingPostShould {

    @Mock Posts postsRepository;
    public static final String ANY_USERNAME = "anyUser";

    @Test
    public void a_user_can_view_the_posts_of_other_user() {
        GetUserPosts getUserPosts = new GetUserPosts(postsRepository);

        List<Post> userPosts = getUserPosts.execute(ANY_USERNAME);

        verify(postsRepository,times(1)).getAllByUsername(ANY_USERNAME);
    }
}
