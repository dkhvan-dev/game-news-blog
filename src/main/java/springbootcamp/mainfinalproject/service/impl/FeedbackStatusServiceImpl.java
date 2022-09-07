package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.repository.FeedbackStatusRepository;
import springbootcamp.mainfinalproject.service.FeedbackStatusService;

@Service
@RequiredArgsConstructor
public class FeedbackStatusServiceImpl implements FeedbackStatusService {

    private final FeedbackStatusRepository feedbackStatusRepository;
}
