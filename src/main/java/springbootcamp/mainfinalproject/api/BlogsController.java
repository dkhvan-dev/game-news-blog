package springbootcamp.mainfinalproject.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springbootcamp.mainfinalproject.model.Blog;
import springbootcamp.mainfinalproject.model.dto.BlogDto;
import springbootcamp.mainfinalproject.service.BlogService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/blogs")
@RequiredArgsConstructor
public class BlogsController {
    private final BlogService blogService;

    @GetMapping()
    public ResponseEntity<List<BlogDto>> getAllBlogs() {
        return new ResponseEntity<>(blogService.getAllBlogs(), HttpStatus.OK);
    }

    @GetMapping("/byStatus/{statusId}")
    public ResponseEntity<List<BlogDto>> getAllBlogsByStatus(@PathVariable(name = "statusId") Long statusId) {
        return new ResponseEntity<>(blogService.getAllBlogsByStatus(statusId), HttpStatus.OK);
    }

    @GetMapping("{blogId}")
    public ResponseEntity<BlogDto> getBlogById(@PathVariable(name = "blogId") Long blogId) {
        return new ResponseEntity<>(blogService.getBlogById(blogId), HttpStatus.OK);
    }

    @GetMapping("/byGame/{gameId}")
    public ResponseEntity<List<BlogDto>> getAllBlogsByGame(@PathVariable(name = "gameId") Long gameId) {
        return new ResponseEntity<>(blogService.getAllBlogsByGame(gameId), HttpStatus.OK);
    }

    @GetMapping("/byPlatform/{platformId}")
    public ResponseEntity<List<BlogDto>> getAllBlogsByPlatform(@PathVariable(name = "platformId") Long platformId) {
        return new ResponseEntity<>(blogService.getAllBlogsByPlatform(platformId), HttpStatus.OK);
    }

    @GetMapping("/byGenre/{genreId}")
    public ResponseEntity<List<BlogDto>> getAllBlogsByGenre(@PathVariable(name = "genreId") Long genreId) {
        return new ResponseEntity<>(blogService.getAllBlogsByGenre(genreId), HttpStatus.OK);
    }

    @GetMapping("/last3Blogs")
    public ResponseEntity<List<BlogDto>> getLast3Blogs() {
        return new ResponseEntity<>(blogService.getTop3Blogs(), HttpStatus.OK);
    }

    @GetMapping("/lastBlogByGame/{gameId}")
    public ResponseEntity<BlogDto> getLast3BlogsByGame(@PathVariable(name = "gameId") Long gameId) {
        return new ResponseEntity<>(blogService.getLastBlogByGame(gameId), HttpStatus.OK);
    }
}
