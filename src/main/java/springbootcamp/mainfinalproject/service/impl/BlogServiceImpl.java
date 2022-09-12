package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import springbootcamp.mainfinalproject.model.Blog;
import springbootcamp.mainfinalproject.model.Game;
import springbootcamp.mainfinalproject.model.User;
import springbootcamp.mainfinalproject.repository.BlogRepository;
import springbootcamp.mainfinalproject.repository.GameRepository;
import springbootcamp.mainfinalproject.service.BlogService;
import springbootcamp.mainfinalproject.service.FileUploadService;
import springbootcamp.mainfinalproject.service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final GameRepository gameRepository;
    private final UserService userService;
    private final FileUploadService fileUploadService;

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAllByOrderByBlogId();
    }

    @Override
    public Blog getBlogById(Long blogId) {
        Blog blog = blogRepository.findById(blogId).orElse(null);
        if (blog != null) {
            return blog;
        }
        return null;
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

    @Override
    public Blog editBlogAdmin(Blog blog, MultipartFile blogImageToken, String blogCreateDate, String blogUpdateDate, Long authorId) {
        if (blog != null) {
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate createDate = LocalDate.parse(blogCreateDate, pattern);
            LocalDate updateDate = LocalDate.parse(blogUpdateDate, pattern);
            User author = userService.getUserById(authorId);
            if (author != null) {
                fileUploadService.uploadBlogImage(blogImageToken, blog);
                blog.setBlogCreateDate(createDate);
                blog.setBlogUpdateDate(updateDate);
                blog.setUsers(author);
                return blogRepository.save(blog);
            }
        }
        return null;
    }

    @Override
    public void deleteBlogAdmin(Long blogId) {
        Blog blog = blogRepository.findById(blogId).orElse(null);
        if (blog != null) {
            blogRepository.deleteById(blogId);
        }
    }
}
