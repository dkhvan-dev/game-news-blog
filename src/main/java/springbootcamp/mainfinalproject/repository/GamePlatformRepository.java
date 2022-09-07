package springbootcamp.mainfinalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootcamp.mainfinalproject.model.GamePlatform;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface GamePlatformRepository extends JpaRepository<GamePlatform, Long> {
    List<GamePlatform> findAllByOrderByPlatformId();
}
