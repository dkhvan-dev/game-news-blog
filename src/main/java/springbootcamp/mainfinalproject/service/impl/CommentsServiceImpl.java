package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.model.Blog;
import springbootcamp.mainfinalproject.model.Comments;
import springbootcamp.mainfinalproject.model.Role;
import springbootcamp.mainfinalproject.repository.CommentsRepository;
import springbootcamp.mainfinalproject.service.BlogService;
import springbootcamp.mainfinalproject.service.CommentsService;
import springbootcamp.mainfinalproject.service.RoleService;
import springbootcamp.mainfinalproject.service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {
    private final CommentsRepository commentsRepository;
    private final BlogService blogService;
    private final UserService userService;
    private final RoleService roleService;

    @Override
    public List<Comments> getAllComments() {
        return commentsRepository.findAllByOrderByCommentCreateDateDesc();
    }

    @Override
    public Comments getCommentById(Long commentId) {
        Comments comment = commentsRepository.findById(commentId).orElse(null);
        if (comment != null) {
            return comment;
        }
        return null;
    }

    @Override
    public Comments addComment(Comments comment, Long blogId) {
        if (comment != null) {
            comment.setCommentCreateDate(LocalDate.now());
            comment.setAuthor(userService.getCurrentUser());
            Blog blog = blogService.getBlogById(blogId);
            comment.setBlog(blog);
            return commentsRepository.save(comment);
        }
        return null;
    }

    @Override
    public void deleteComment(Long commentId) {
        Comments checkComment = commentsRepository.findById(commentId).orElse(null);
        if (checkComment != null) {
            commentsRepository.delete(checkComment);
        }
    }
}
