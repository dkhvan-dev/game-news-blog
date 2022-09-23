package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.model.Feedback;
import springbootcamp.mainfinalproject.model.FeedbackStatus;
import springbootcamp.mainfinalproject.repository.FeedbackRepository;
import springbootcamp.mainfinalproject.service.FeedbackService;
import springbootcamp.mainfinalproject.service.FeedbackStatusService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackStatusService feedbackStatusService;

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAllByOrderByFeedbackId();
    }

    @Override
    public Feedback getFeedbackById(Long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId).orElse(null);
        if (feedback != null) {
            return feedback;
        }
        return null;
    }

    @Override
    public Feedback addFeedback(Feedback feedback) {
        if (feedback != null) {
            FeedbackStatus feedbackStatus = feedbackStatusService.getFeedbackStatusById(1L);
            if (feedbackStatus != null) {
                feedback.setFeedbackStatus(feedbackStatus);
                return feedbackRepository.save(feedback);
            }
        }
        return null;
    }

    @Override
    public Feedback editFeedback(Feedback feedback) {
        if (feedback != null) {
            return feedbackRepository.save(feedback);
        }
        return null;
    }

    @Override
    public void deleteFeedback(Long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId).orElse(null);
        if (feedback != null) {
            feedbackRepository.deleteById(feedbackId);
        }
    }
}
