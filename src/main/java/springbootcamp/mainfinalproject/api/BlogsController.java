package springbootcamp.mainfinalproject.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootcamp.mainfinalproject.model.Blog;
import springbootcamp.mainfinalproject.service.BlogService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/blogs")
@RequiredArgsConstructor
public class BlogsController {
    private final BlogService blogService;

    @GetMapping()
    public ResponseEntity<List<Blog>> getAllBlogs() {
        return new ResponseEntity<>(blogService.getAllBlogs(), HttpStatus.OK);
    }

    @GetMapping("{blogId}")
    public ResponseEntity<Blog> getBlogById(@PathVariable(name = "blogId") Long blogId) {
        return new ResponseEntity<>(blogService.getBlogById(blogId), HttpStatus.OK);
    }

    @GetMapping("/byGame/{gameId}")
    public ResponseEntity<List<Blog>> getAllBlogsByGame(@PathVariable(name = "gameId") Long gameId) {
        return new ResponseEntity<>(blogService.getAllBlogsByGame(gameId), HttpStatus.OK);
    }

    @GetMapping("/byPlatform/{platformId}")
    public ResponseEntity<List<Blog>> getAllBlogsByPlatform(@PathVariable(name = "platformId") Long platformId) {
        return new ResponseEntity<>(blogService.getAllBlogsByPlatform(platformId), HttpStatus.OK);
    }

    @GetMapping("/byGenre/{genreId}")
    public ResponseEntity<List<Blog>> getAllBlogsByGenre(@PathVariable(name = "genreId") Long genreId) {
        return new ResponseEntity<>(blogService.getAllBlogsByGenre(genreId), HttpStatus.OK);
    }

    @GetMapping("/last3Blogs")
    public ResponseEntity<List<Blog>> getLast3Blogs() {
        return new ResponseEntity<>(blogService.getTop3Blogs(), HttpStatus.OK);
    }

    @GetMapping("/lastBlogByGame/{gameId}")
    public ResponseEntity<Blog> getLast3BlogsByGame(@PathVariable(name = "gameId") Long gameId) {
        return new ResponseEntity<>(blogService.getLastBlogByGame(gameId), HttpStatus.OK);
    }
}
