package springbootcamp.mainfinalproject.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springbootcamp.mainfinalproject.model.Blog;
import springbootcamp.mainfinalproject.model.Comments;
import springbootcamp.mainfinalproject.model.dto.BlogDto;
import springbootcamp.mainfinalproject.service.BlogService;
import springbootcamp.mainfinalproject.service.CommentsService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentsController {
    private final CommentsService commentsService;
    private final BlogService blogService;

    @GetMapping()
    public ResponseEntity<List<Comments>> getAllComments() {
        return new ResponseEntity<>(commentsService.getAllComments(), HttpStatus.OK);
    }

    @GetMapping("{commentId}")
    public ResponseEntity<Comments> getCommentById(@PathVariable(name = "commentId") Long commentId) {
        return new ResponseEntity<>(commentsService.getCommentById(commentId), HttpStatus.OK);
    }

    @GetMapping("/byBlog/{blogId}")
    public ResponseEntity<List<Comments>> getAllComments(@PathVariable(name = "blogId") Long blogId) {
        return new ResponseEntity<>(commentsService.getAllCommentsByBlog(blogId), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() && !hasRole('ROLE_BAN')")
    @PostMapping("/addComment")
    public ResponseEntity<String> addComment(@RequestParam(name = "tinyMceTextarea") String commentText,
                                             @RequestParam(name = "blogId") Long blogId) {
        BlogDto blog = blogService.getBlogById(blogId);
        if (blog != null) {
            Comments newComment = commentsService.addComment(commentText, blogId);
        }
        return new ResponseEntity<>("Comment was added", HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated() && !hasRole('ROLE_BAN')")
    @PostMapping("/deleteComment")
    public ResponseEntity<String> deleteComment(@RequestParam(name = "commentId") Long commentId) {
        Comments comment = commentsService.getCommentById(commentId);
        if (comment != null) {
            commentsService.deleteComment(commentId);
        }
        return new ResponseEntity<>("Comment was deleted", HttpStatus.OK);
    }
}
