package com.feature;

public class PublishMessageIntoTimeline {
    private final Users users;
    private final TimelineRepository timelineRepository;

    public PublishMessageIntoTimeline(Users users, TimelineRepository timelineRepository) {

        this.users = users;
        this.timelineRepository = timelineRepository;
    }

    public void execute(String username, String message) {
        if (users.exist(username)) {
            timelineRepository.addMessage(username, message);
        }
    }
}
