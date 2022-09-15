package springbootcamp.mainfinalproject.service;

import org.springframework.web.multipart.MultipartFile;
import springbootcamp.mainfinalproject.model.Blog;
import springbootcamp.mainfinalproject.model.Game;
import springbootcamp.mainfinalproject.model.User;

public interface FileUploadService {
    boolean uploadGameImage(MultipartFile multipartFile, Game game);
    boolean uploadBlogImage(MultipartFile multipartFile, Blog blog);
}
