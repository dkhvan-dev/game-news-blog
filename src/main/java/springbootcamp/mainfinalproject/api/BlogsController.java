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
}
