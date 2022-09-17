package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.model.BlogStatus;
import springbootcamp.mainfinalproject.repository.BlogStatusRepository;
import springbootcamp.mainfinalproject.service.BlogStatusService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogStatusServiceImpl implements BlogStatusService {

    private final BlogStatusRepository blogStatusRepository;

    @Override
    public List<BlogStatus> getAllStatus() {
        return blogStatusRepository.findAll();
    }

    @Override
    public BlogStatus getStatusById(Long blogStatusId) {
        BlogStatus blogStatus = blogStatusRepository.findById(blogStatusId).orElse(null);
        if (blogStatus != null) {
            return blogStatus;
        }
        return null;
    }
}
