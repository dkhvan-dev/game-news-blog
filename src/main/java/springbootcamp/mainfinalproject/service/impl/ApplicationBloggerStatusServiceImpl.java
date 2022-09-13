package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.model.ApplicationBloggerStatus;
import springbootcamp.mainfinalproject.repository.ApplicationBloggerStatusRepository;
import springbootcamp.mainfinalproject.repository.GameRepository;
import springbootcamp.mainfinalproject.service.ApplicationBloggerStatusService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationBloggerStatusServiceImpl implements ApplicationBloggerStatusService {

    private final ApplicationBloggerStatusRepository applicationBloggerStatusRepository;

    @Override
    public List<ApplicationBloggerStatus> getAllBloggerStatus() {
        return applicationBloggerStatusRepository.findAll();
    }

    @Override
    public ApplicationBloggerStatus getApplicationBloggerStatusById(Long applicationBloggerStatusId) {
        ApplicationBloggerStatus checkBloggerStatus = applicationBloggerStatusRepository.findById(applicationBloggerStatusId).orElse(null);
        if (checkBloggerStatus != null) {
            return checkBloggerStatus;
        }
        return null;
    }
}
