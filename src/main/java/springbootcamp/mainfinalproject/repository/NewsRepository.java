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
    List<News> findAllByOrderByNewsCreateDateDesc();

    @Query(nativeQuery = true, value = "SELECT * FROM news ORDER BY news.create_date DESC LIMIT 1")
    News findLastNews();

    @Query(nativeQuery = true, value = "SELECT * FROM news JOIN games_platform gp on news.game_id = gp.game_id WHERE gp.platform_id = :platformId ORDER BY news.create_date DESC LIMIT 1")
    News findLastNewsByPlatform(Long platformId);
    @Query(nativeQuery = true, value = "SELECT * FROM news JOIN games_genres gg on news.game_id = gg.game_id JOIN genres g on gg.genres_id = g.id WHERE gg.genres_id = :genreId ORDER BY news.create_date DESC LIMIT 1")
    News findLastNewsByGenre(Long genreId);
    List<News> findAllByGameGameId(Long gameId);
    @Query(nativeQuery = true, value = "SELECT * FROM news JOIN games_platform gp on news.game_id = gp.game_id JOIN platform p on gp.platform_id = p.id WHERE gp.platform_id = :platformId")
    List<News> findAllNewsByPlatform(Long platformId);
    @Query(nativeQuery = true, value = "SELECT * FROM news JOIN games g on news.game_id = g.id JOIN games_genres gg on g.id = gg.game_id WHERE gg.genres_id = :genreId")
    List<News> findAllNewsByGenre(Long genreId);
    @Query(nativeQuery = true, value = "SELECT * FROM news ORDER BY news.create_date DESC LIMIT 3")
    List<News> findLatest3News();
    @Query(nativeQuery = true, value = "SELECT * FROM news WHERE news.game_id = :gameId ORDER BY create_date DESC")
    List<News> findLatest3NewsByGame(Long gameId);
}
