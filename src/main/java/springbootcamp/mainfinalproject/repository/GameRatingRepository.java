package springbootcamp.mainfinalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootcamp.mainfinalproject.model.GameRating;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface GameRatingRepository extends JpaRepository<GameRating, Long> {
}
