package springbootcamp.mainfinalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootcamp.mainfinalproject.model.BlogStatus;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface BlogStatusRepository extends JpaRepository<BlogStatus, Long> {
}
