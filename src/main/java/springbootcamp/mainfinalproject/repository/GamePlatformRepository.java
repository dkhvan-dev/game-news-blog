package springbootcamp.mainfinalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springbootcamp.mainfinalproject.model.GamePlatform;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface GamePlatformRepository extends JpaRepository<GamePlatform, Long> {
    List<GamePlatform> findAllByOrderByPlatformId();

    @Query(nativeQuery = true, value = "SELECT * FROM platform JOIN games_platform gp on platform.id = gp.platform_id WHERE gp.game_id = :gameId")
    List<GamePlatform> findAllByGame(Long gameId);
}
