package springbootcamp.mainfinalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootcamp.mainfinalproject.model.ApplicationFormBlogger;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ApplicationFormBloggerRepository extends JpaRepository<ApplicationFormBlogger, Long> {
    List<ApplicationFormBlogger> findAllByOrderByApplicationFormBloggerReceiptDate();
}
