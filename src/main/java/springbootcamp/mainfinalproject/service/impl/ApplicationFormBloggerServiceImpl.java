package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.model.ApplicationFormBlogger;
import springbootcamp.mainfinalproject.repository.ApplicationFormBloggerRepository;
import springbootcamp.mainfinalproject.repository.GameRepository;
import springbootcamp.mainfinalproject.service.ApplicationFormBloggerService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationFormBloggerServiceImpl implements ApplicationFormBloggerService {

    private final ApplicationFormBloggerRepository applicationFormBloggerRepository;

    @Override
    public List<ApplicationFormBlogger> getAllApplications() {
        return applicationFormBloggerRepository.findAllByOrderByApplicationFormBloggerReceiptDate();
    }
}
