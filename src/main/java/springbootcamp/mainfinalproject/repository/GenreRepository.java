package springbootcamp.mainfinalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootcamp.mainfinalproject.model.Genre;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface GenreRepository extends JpaRepository<Genre, Long> {
    List<Genre> findAllByOrderByGenreId();
}
