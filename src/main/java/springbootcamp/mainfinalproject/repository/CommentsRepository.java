package springbootcamp.mainfinalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootcamp.mainfinalproject.model.Comments;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    List<Comments> findAllByOrderByCommentCreateDateDesc();
}
