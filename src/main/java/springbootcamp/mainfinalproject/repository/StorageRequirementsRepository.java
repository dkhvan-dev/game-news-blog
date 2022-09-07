package springbootcamp.mainfinalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootcamp.mainfinalproject.model.OsRequirements;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface OsRequirementsRepository extends JpaRepository<OsRequirements, Long> {
    List<OsRequirements> findAllByPlatform_PlatformId(Long platformId);
}
