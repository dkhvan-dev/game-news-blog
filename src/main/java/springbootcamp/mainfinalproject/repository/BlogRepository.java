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
    List<Blog> findAllByOrderByBlogCreateDateDesc();
    @Query(nativeQuery = true, value = "SELECT * FROM blogs ORDER BY blogs.create_date DESC LIMIT 3")
    List<Blog> findAllTop3OrderByCreateDateDesc();
    @Query(nativeQuery = true, value = "SELECT * FROM blogs JOIN games g on g.id = blogs.games_id ORDER BY blogs.create_date DESC LIMIT 1")
    Blog findLastBlogByGame(Long gameId);
    List<Blog> searchAllByGames_GameIdOrderByBlogCreateDateDesc(Long gameId);
    List<Blog> findAllByGames_GameId(Long gameId);
    @Query(nativeQuery = true, value = "SELECT * FROM blogs JOIN games_platform gp ON gp.game_id = blogs.games_id WHERE gp.platform_id = :platformId ORDER BY blogs.create_date DESC")
    List<Blog> findAllBlogsByPlatform(Long platformId);
    @Query(nativeQuery = true, value = "SELECT * FROM blogs JOIN games_genres gg ON gg.game_id = blogs.games_id WHERE gg.genres_id = :genreId ORDER BY blogs.create_date DESC")
    List<Blog> findAllBlogsByGenre(Long genreId);
}
