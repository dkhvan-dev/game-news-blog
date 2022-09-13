package springbootcamp.mainfinalproject.service;

import springbootcamp.mainfinalproject.model.ApplicationBloggerStatus;

import java.util.List;

public interface ApplicationBloggerStatusService {
    List<ApplicationBloggerStatus> getAllBloggerStatus();
    ApplicationBloggerStatus getApplicationBloggerStatusById(Long applicationBloggerStatusId);
}
