package springbootcamp.mainfinalproject.service;

import springbootcamp.mainfinalproject.model.FeedbackStatus;

import java.util.List;

public interface FeedbackStatusService {
    List<FeedbackStatus> getAllFeedbackStatus();
    FeedbackStatus getFeedbackStatusById(Long feedbackStatusId);
}
