package springbootcamp.mainfinalproject.service;

import springbootcamp.mainfinalproject.model.Feedback;

import java.util.List;

public interface FeedbackService {
    List<Feedback> getAllFeedback();
    Feedback getFeedbackById(Long feedbackId);
    Feedback addFeedback(Feedback feedback);
    Feedback editFeedback(Feedback feedback);
    void deleteFeedback(Long feedbackId);
}
