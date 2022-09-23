package springbootcamp.mainfinalproject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springbootcamp.mainfinalproject.model.Feedback;
import springbootcamp.mainfinalproject.service.FeedbackService;

@SpringBootTest
public class FeedbackTests {
    @Autowired
    private FeedbackService feedbackService;

    @Test
    void testSendFeedback() {
        Feedback feedback = new Feedback();
        feedback.setFeedbackEmail("test@mail.ru");
        feedback.setFeedbackDescription("Test description");
        feedback.setFeedbackSubject("Test subject");
        feedback.setFeedbackName("Test user");
        Feedback newFeedback = feedbackService.addFeedback(feedback);
        Assertions.assertNotNull(newFeedback);
        Assertions.assertNotNull(newFeedback.getFeedbackId());
        Assertions.assertNotNull(newFeedback.getFeedbackDescription());
        Assertions.assertNotNull(newFeedback.getFeedbackEmail());
        Assertions.assertNotNull(newFeedback.getFeedbackName());
        Assertions.assertNotNull(newFeedback.getFeedbackSubject());
        Assertions.assertNotNull(newFeedback.getFeedbackStatus());
        feedbackService.deleteFeedback(newFeedback.getFeedbackId());
    }
}
