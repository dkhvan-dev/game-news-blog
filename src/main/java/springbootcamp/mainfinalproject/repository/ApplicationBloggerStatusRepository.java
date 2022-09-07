package springbootcamp.mainfinalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootcamp.mainfinalproject.model.ApplicationBloggerStatus;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ApplicationBloggerStatusRepository extends JpaRepository<ApplicationBloggerStatus, Long> {
}
