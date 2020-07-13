package com.jlopez.actions;

import com.jlopez.domain.Timelines;

public class PublishMessageIntoTimeline {


    private Timelines timelines;

    public PublishMessageIntoTimeline(Timelines timelines) {

        this.timelines = timelines;
    }

    public void execute(String username, String message) {
        timelines.addMessage(username, message);
    }
}
