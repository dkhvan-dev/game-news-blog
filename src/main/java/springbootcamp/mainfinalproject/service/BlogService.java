package springbootcamp.mainfinalproject.service;

import org.springframework.web.multipart.MultipartFile;
import springbootcamp.mainfinalproject.model.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> getAllBlogs();
    Blog getBlogById(Long blogId);
    List<Blog> getTop3Blogs();
    List<Blog> getAllBlogsByGame(Long gameId);
    Blog editBlogAdmin(Blog blog, MultipartFile blogImageToken, String blogCreateDate, String blogUpdateDate, Long authorId);
    void deleteBlogAdmin(Long blogId);
}
