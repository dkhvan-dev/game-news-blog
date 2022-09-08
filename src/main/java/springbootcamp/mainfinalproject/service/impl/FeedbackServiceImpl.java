package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.model.Feedback;
import springbootcamp.mainfinalproject.repository.FeedbackRepository;
import springbootcamp.mainfinalproject.service.FeedbackService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAllByOrderByFeedbackReceiptDate();
    }
}
