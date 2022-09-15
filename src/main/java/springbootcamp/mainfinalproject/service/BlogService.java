package springbootcamp.mainfinalproject.service;

import org.springframework.web.multipart.MultipartFile;
import springbootcamp.mainfinalproject.model.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> getAllBlogs();
    Blog getBlogById(Long blogId);
    List<Blog> getTop3Blogs();
    Blog getLastBlogByGame(Long gameId);
    List<Blog> getAllBlogsByGame(Long gameId);
    List<Blog> getAllBlogsByPlatform(Long platformId);
    List<Blog> getAllBlogsByGenre(Long genreId);
    Blog addBlog(Blog blog, MultipartFile blogImageToken);
    Blog editBlogAdmin(Blog blog, MultipartFile blogImageToken, String blogCreateDate, String blogUpdateDate, Long authorId);
    void deleteBlogAdmin(Long blogId);
}
