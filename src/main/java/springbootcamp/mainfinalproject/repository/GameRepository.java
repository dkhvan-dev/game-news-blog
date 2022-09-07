package springbootcamp.mainfinalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootcamp.mainfinalproject.model.Game;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findAllByOrderByGameId();
}
