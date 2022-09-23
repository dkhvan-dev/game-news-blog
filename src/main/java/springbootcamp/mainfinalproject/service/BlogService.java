package springbootcamp.mainfinalproject.service;

import org.springframework.web.multipart.MultipartFile;
import springbootcamp.mainfinalproject.model.Blog;
import springbootcamp.mainfinalproject.model.dto.BlogDto;

import java.util.List;

public interface BlogService {
    List<BlogDto> getAllBlogs();
    List<BlogDto> getAllBlogsByStatus(Long statusId);
    List<BlogDto> getAllBlogsByUser(Long userId);
    BlogDto getBlogById(Long blogId);
    List<BlogDto> getTop3Blogs();
    BlogDto getLastBlogByGame(Long gameId, Long statusId);
    List<BlogDto> getAllBlogsByGame(Long gameId);
    List<BlogDto> getAllBlogsByPlatform(Long platformId);
    List<BlogDto> getAllBlogsByGenre(Long genreId);
    Blog addBlog(Blog blog, MultipartFile blogImageToken);
    Blog editBlogAdmin(Blog blog, MultipartFile blogImageToken, String blogCreateDate, Long authorId);
    Blog editBlog(Blog blog, MultipartFile blogImageToken, String blogCreateDate, Long authorId);
    void deleteBlog(Long blogId);
}
