package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.model.ApplicationBloggerStatus;
import springbootcamp.mainfinalproject.model.ApplicationFormBlogger;
import springbootcamp.mainfinalproject.model.Role;
import springbootcamp.mainfinalproject.model.User;
import springbootcamp.mainfinalproject.repository.ApplicationFormBloggerRepository;
import springbootcamp.mainfinalproject.repository.GameRepository;
import springbootcamp.mainfinalproject.service.ApplicationBloggerStatusService;
import springbootcamp.mainfinalproject.service.ApplicationFormBloggerService;
import springbootcamp.mainfinalproject.service.RoleService;
import springbootcamp.mainfinalproject.service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationFormBloggerServiceImpl implements ApplicationFormBloggerService {

    private final ApplicationFormBloggerRepository applicationFormBloggerRepository;
    private final ApplicationBloggerStatusService applicationBloggerStatusService;
    private final RoleService roleService;
    private final UserService userService;

    @Override
    public List<ApplicationFormBlogger> getAllApplications() {
        return applicationFormBloggerRepository.findAllByOrderByApplicationFormBloggerReceiptDate();
    }

    @Override
    public ApplicationFormBlogger getApplicationFormById(Long applicationFormId) {
        ApplicationFormBlogger checkApplication = applicationFormBloggerRepository.findById(applicationFormId).orElse(null);
        if (checkApplication != null) {
            return checkApplication;
        }
        return null;
    }

    @Override
    public ApplicationFormBlogger addApplicationFormBlogger(ApplicationFormBlogger applicationFormBlogger) {
        ApplicationBloggerStatus defaultBloggerStatus = applicationBloggerStatusService.getApplicationBloggerStatusById(1L);
        applicationFormBlogger.setApplicationBloggerStatus(defaultBloggerStatus);
        applicationFormBlogger.setUsers(userService.getCurrentUser());
        applicationFormBlogger.setApplicationFormBloggerReceiptDate(LocalDate.now());
        return applicationFormBloggerRepository.save(applicationFormBlogger);
    }

    @Override
    public ApplicationFormBlogger updateApplicationForm(ApplicationFormBlogger applicationFormBlogger, String applicationFormBloggerReceiptDate, String applicationFormBloggerUpdateDate, Long authorId) {
        if (applicationFormBlogger != null) {
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate receiptDate = LocalDate.parse(applicationFormBloggerReceiptDate, pattern);
            LocalDate updateDate = LocalDate.now();
            if (!applicationFormBloggerUpdateDate.equals("")) {
                updateDate = LocalDate.parse(applicationFormBloggerUpdateDate, pattern);
            }
            User author = userService.getUserById(authorId);
            applicationFormBlogger.setApplicationFormBloggerReceiptDate(receiptDate);
            applicationFormBlogger.setApplicationFormBloggerUpdateDate(updateDate);
            applicationFormBlogger.setUsers(author);
            if (applicationFormBlogger.getApplicationBloggerStatus().getApplicationBloggerStatusId() == 2) {
                Role role = roleService.getRoleById(2L);
                if (!applicationFormBlogger.getUsers().getRoles().contains(role)) {
                    applicationFormBlogger.getUsers().getRoles().add(role);
                }
            }
            return applicationFormBloggerRepository.save(applicationFormBlogger);
        }
        return null;
    }
}
