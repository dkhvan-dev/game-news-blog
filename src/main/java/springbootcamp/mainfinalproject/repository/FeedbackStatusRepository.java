package springbootcamp.mainfinalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootcamp.mainfinalproject.model.FeedbackStatus;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FeedbackStatusRepository extends JpaRepository<FeedbackStatus, Long> {
}
