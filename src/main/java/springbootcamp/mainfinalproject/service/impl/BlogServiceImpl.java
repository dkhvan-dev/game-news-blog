package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.model.Blog;
import springbootcamp.mainfinalproject.model.Game;
import springbootcamp.mainfinalproject.repository.BlogRepository;
import springbootcamp.mainfinalproject.repository.GameRepository;
import springbootcamp.mainfinalproject.service.BlogService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final GameRepository gameRepository;

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAllByOrderByBlogId();
    }

    @Override
    public List<Blog> getTop3Blogs() {
        return blogRepository.findAllTop3OrderByCreateDateDesc();
    }

    @Override
    public List<Blog> getAllBlogsByGame(Long gameId) {
        Game checkGame = gameRepository.findById(gameId).orElse(null);
        if (checkGame != null) {
            return blogRepository.searchAllByGames_GameIdOrderByBlogCreateDateDesc(gameId);
        }
        return null;
    }


}
