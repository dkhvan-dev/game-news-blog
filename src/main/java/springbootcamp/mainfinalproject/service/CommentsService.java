package springbootcamp.mainfinalproject.service;

import springbootcamp.mainfinalproject.model.Comments;

import java.util.List;

public interface CommentsService {
    List<Comments> getAllComments();
    List<Comments> getAllCommentsByBlog(Long blogId);
    Comments getCommentById(Long commentId);
    Comments addComment(String commentText, Long blogId);
    void deleteComment(Long commentId);
}
