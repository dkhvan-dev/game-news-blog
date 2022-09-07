package springbootcamp.mainfinalproject.service;

import springbootcamp.mainfinalproject.model.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> getAllBlogs();
    List<Blog> getTop3Blogs();
    List<Blog> getAllBlogsByGame(Long gameId);
}
