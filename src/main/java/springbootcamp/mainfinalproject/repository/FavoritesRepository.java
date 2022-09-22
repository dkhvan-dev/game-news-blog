package springbootcamp.mainfinalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootcamp.mainfinalproject.model.Favorites;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
    List<Favorites> findAllByUserUserIdOrderByBlogsBlogCreateDateDesc(Long userId);
    Favorites findByUserUserIdAndBlogsBlogId(Long userId, Long blogId);
}
