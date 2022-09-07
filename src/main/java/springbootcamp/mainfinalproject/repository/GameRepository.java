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
}
