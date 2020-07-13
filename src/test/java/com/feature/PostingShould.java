package com.feature;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PostingShould {

    public static final String ANY_USERNAME = "anyUser";
    public static final String ANY_MESSAGE = "message";
    @Mock Users users;
    @Mock TimelineRepository timelineRepository;
    private PublishMessageIntoTimeline publishMessageIntoTimeline;

    @Test
    public void a_user_can_publish_messages_to_a_personal_timeline() {
        publishMessageIntoTimeline = new PublishMessageIntoTimeline(users, timelineRepository);
        given(users.exist(ANY_USERNAME)).willReturn(true);

        publishMessageIntoTimeline.execute(ANY_USERNAME, ANY_MESSAGE);

        //TODO: Validar en KATA
        verify(users).exist(ANY_USERNAME);
        verify(timelineRepository).addMessage(ANY_USERNAME, ANY_MESSAGE);

    }

}
