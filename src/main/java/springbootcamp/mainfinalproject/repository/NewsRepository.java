package springbootcamp.mainfinalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootcamp.mainfinalproject.model.News;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findAllByOrderByNewsCreateDate();
}
