package springbootcamp.mainfinalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springbootcamp.mainfinalproject.model.Game;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findAllByOrderByGameId();

    @Query(nativeQuery = true, value = "SELECT * FROM games g JOIN games_platform gp on g.id = gp.game_id WHERE gp.platform_id = :platformId")
    List<Game> findAllByPlatformId(Long platformId);
    @Query(nativeQuery = true, value = "SELECT * FROM games g JOIN games_genres gg on g.id = gg.game_id WHERE gg.genres_id = :genreId")
    List<Game> findAllByGenreId(Long genreId);

    @Query(nativeQuery = true, value = "SELECT * FROM games g JOIN game_ratings gr on g.game_ratings_id = gr.id ORDER BY gr.avg DESC LIMIT 5")
    List<Game> searchTop5Games();
}
