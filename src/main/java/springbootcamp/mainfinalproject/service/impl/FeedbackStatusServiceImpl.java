package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.model.FeedbackStatus;
import springbootcamp.mainfinalproject.repository.FeedbackStatusRepository;
import springbootcamp.mainfinalproject.service.FeedbackStatusService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackStatusServiceImpl implements FeedbackStatusService {

    private final FeedbackStatusRepository feedbackStatusRepository;

    @Override
    public List<FeedbackStatus> getAllFeedbackStatus() {
        return feedbackStatusRepository.findAll();
    }

    @Override
    public FeedbackStatus getFeedbackStatusById(Long feedbackStatusId) {
        FeedbackStatus feedbackStatus = feedbackStatusRepository.findById(feedbackStatusId).orElse(null);
        if (feedbackStatus != null) {
            return feedbackStatus;
        }
        return null;
    }
}
