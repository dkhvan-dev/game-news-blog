package springbootcamp.mainfinalproject.service;

import springbootcamp.mainfinalproject.model.BlogStatus;

import java.util.List;

public interface BlogStatusService {
    List<BlogStatus> getAllStatus();
    BlogStatus getStatusById(Long blogStatusId);
}
