package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import springbootcamp.mainfinalproject.mapper.BlogMapper;
import springbootcamp.mainfinalproject.model.*;
import springbootcamp.mainfinalproject.model.dto.BlogDto;
import springbootcamp.mainfinalproject.repository.BlogRepository;
import springbootcamp.mainfinalproject.repository.GameRepository;
import springbootcamp.mainfinalproject.service.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final GameService gameService;
    private final GamePlatformService gamePlatformService;
    private final GenreService genreService;
    private final UserService userService;
    private final FileUploadService fileUploadService;
    private final BlogStatusService blogStatusService;
    private final BlogMapper blogMapper;

    @Override
    public List<BlogDto> getAllBlogs() {
        return blogMapper.toDtoList(blogRepository.findAllByBlogStatusBlogStatusNameOrderByBlogCreateDateDesc("APPROVED"));
    }

    @Override
    public List<BlogDto> getAllBlogsByStatus(Long statusId) {
        BlogStatus blogStatus = blogStatusService.getStatusById(statusId);
        if (blogStatus != null) {
            return blogMapper.toDtoList(blogRepository.findAllByBlogStatusBlogStatusNameOrderByBlogCreateDateDesc(blogStatus.getBlogStatusName()));
        }
        return null;
    }

    @Override
    public BlogDto getBlogById(Long blogId) {
        Blog blog = blogRepository.findById(blogId).orElse(null);
        if (blog != null) {
            return blogMapper.toDto(blog);
        }
        return null;
    }

    @Override
    public List<BlogDto> getTop3Blogs() {
        return blogMapper.toDtoList(blogRepository.findAllTop3OrderByCreateDateDesc());
    }

    @Override
    public BlogDto getLastBlogByGame(Long gameId) {
        Game checkGame = gameService.getGameById(gameId);
        if (checkGame != null) {
            return blogMapper.toDto(blogRepository.findLastBlogByGame(gameId));
        }
        return null;
    }

    @Override
    public List<BlogDto> getAllBlogsByGame(Long gameId) {
        Game checkGame = gameService.getGameById(gameId);
        if (checkGame != null) {
            return blogMapper.toDtoList(blogRepository.searchAllByGames_GameIdAndBlogStatusBlogStatusNameOrderByBlogCreateDateDesc(gameId, "APPROVED"));
        }
        return null;
    }

    @Override
    public List<BlogDto> getAllBlogsByPlatform(Long platformId) {
        GamePlatform platform = gamePlatformService.getPlatformById(platformId);
        if (platform != null) {
            return blogMapper.toDtoList(blogRepository.findAllBlogsByPlatform(platformId));
        }
        return null;
    }

    @Override
    public List<BlogDto> getAllBlogsByGenre(Long genreId) {
        Genre checkGenre = genreService.getGenreById(genreId);
        if (checkGenre != null) {
            return blogMapper.toDtoList(blogRepository.findAllBlogsByGenre(genreId));
        }
        return null;
    }

    @Override
    public Blog addBlog(Blog blog, MultipartFile blogImageToken) {
        if (blog.getBlogId() == null) {
            fileUploadService.uploadBlogImage(blogImageToken, blog);
            blog.setBlogCreateDate(LocalDate.now());
            blog.setUsers(userService.getCurrentUser());
            BlogStatus blogStatus = blogStatusService.getStatusById(1L);
            blog.setBlogStatus(blogStatus);
            return blogRepository.save(blog);
        }
        return null;
    }

    @Override
    public Blog editBlogAdmin(Blog blog, MultipartFile blogImageToken, String blogCreateDate, String blogUpdateDate, Long authorId) {
        if (blog != null) {
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate createDate = LocalDate.parse(blogCreateDate, pattern);
            if (!blogUpdateDate.equals("")) {
                LocalDate updateDate = LocalDate.parse(blogUpdateDate, pattern);
                blog.setBlogUpdateDate(updateDate);
            } else {
                blog.setBlogUpdateDate(null);
            }
            User author = userService.getUserById(authorId);
            if (author != null) {
                fileUploadService.uploadBlogImage(blogImageToken, blog);
                blog.setBlogCreateDate(createDate);
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
