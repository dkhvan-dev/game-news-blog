package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.repository.ApplicationFormBloggerRepository;
import springbootcamp.mainfinalproject.repository.GameRepository;
import springbootcamp.mainfinalproject.service.ApplicationFormBloggerService;

@Service
@RequiredArgsConstructor
public class ApplicationFormBloggerServiceImpl implements ApplicationFormBloggerService {

    private final ApplicationFormBloggerRepository applicationFormBloggerRepository;
}
