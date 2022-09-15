package springbootcamp.mainfinalproject.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootcamp.mainfinalproject.model.Comments;
import springbootcamp.mainfinalproject.service.CommentsService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentsController {
    private final CommentsService commentsService;

    @GetMapping()
    public ResponseEntity<List<Comments>> getAllComments() {
        return new ResponseEntity<>(commentsService.getAllComments(), HttpStatus.OK);
    }

    @GetMapping("{commentId}")
    public ResponseEntity<Comments> getCommentById(@PathVariable(name = "commentId") Long commentId) {
        return new ResponseEntity<>(commentsService.getCommentById(commentId), HttpStatus.OK);
    }
}
