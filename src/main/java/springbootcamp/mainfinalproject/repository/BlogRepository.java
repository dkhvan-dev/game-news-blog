package springbootcamp.mainfinalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springbootcamp.mainfinalproject.model.Blog;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findAllByOrderByBlogId();
    @Query(nativeQuery = true, value = "SELECT * FROM blogs ORDER BY blogs.create_date DESC LIMIT 3")
    List<Blog> findAllTop3OrderByCreateDateDesc();
    List<Blog> searchAllByGames_GameIdOrderByBlogCreateDateDesc(Long gameId);
}
