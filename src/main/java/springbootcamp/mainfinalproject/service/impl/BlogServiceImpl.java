package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.model.Blog;
import springbootcamp.mainfinalproject.repository.BlogRepository;
import springbootcamp.mainfinalproject.service.BlogService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAllByOrderByBlogId();
    }

    @Override
    public List<Blog> getTop3Blogs() {
        return blogRepository.findAllTop3OrderByCreateDate();
    }

    @Override
    public List<Blog> getAllBlogsByGame(Long gameId) {
        return blogRepository.searchAllByGames_GameIdOrderByBlogId(gameId);
    }


}
