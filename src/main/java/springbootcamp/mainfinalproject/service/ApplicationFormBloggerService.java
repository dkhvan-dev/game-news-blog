package springbootcamp.mainfinalproject.service;

import springbootcamp.mainfinalproject.model.ApplicationFormBlogger;

import java.util.List;

public interface ApplicationFormBloggerService {
    List<ApplicationFormBlogger> getAllApplications();
    ApplicationFormBlogger getApplicationFormById(Long applicationFormId);
    ApplicationFormBlogger addApplicationFormBlogger(ApplicationFormBlogger applicationFormBlogger);
    ApplicationFormBlogger updateApplicationForm(ApplicationFormBlogger applicationFormBlogger, String applicationFormBloggerReceiptDate, String applicationFormBloggerUpdateDate, Long authorId);
}
