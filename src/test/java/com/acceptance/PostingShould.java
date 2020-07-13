package com.acceptance;

import com.jlopez.actions.PublishMessageIntoTimeline;
import com.jlopez.TimelineRepository;
import com.jlopez.domain.Timelines;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PostingShould {

    public static final String ANY_USERNAME = "anyUser";
    public static final String ANY_MESSAGE = "message";

    @Mock Timelines timelines;
    private PublishMessageIntoTimeline publishMessageIntoTimeline;

    @Test
    public void a_user_can_publish_messages_to_a_personal_timeline() {
        publishMessageIntoTimeline = new PublishMessageIntoTimeline(timelines);

        publishMessageIntoTimeline.execute(ANY_USERNAME, ANY_MESSAGE);

        verify(timelines).addMessage(ANY_USERNAME, ANY_MESSAGE);

    }

}
