package springbootcamp.mainfinalproject.service;

import springbootcamp.mainfinalproject.model.Comments;

import java.util.List;

public interface CommentsService {
    List<Comments> getAllComments();
    Comments getCommentById(Long commentId);
    Comments addComment(Comments comments, Long blogId);
    void deleteComment(Long commentId);
}
