package springbootcamp.mainfinalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springbootcamp.mainfinalproject.model.News;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findAllByOrderByNewsCreateDate();

    @Query(nativeQuery = true, value = "SELECT * FROM news ORDER BY news.create_date DESC LIMIT 1")
    News findLastNews();

    @Query(nativeQuery = true, value = "SELECT * FROM news JOIN games_platform gp on news.game_id = gp.game_id WHERE gp.platform_id = :platformId ORDER BY news.create_date DESC LIMIT 1")
    News findLastNewsByPlatform(Long platformId);
}
