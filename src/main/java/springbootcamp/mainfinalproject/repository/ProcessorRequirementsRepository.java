package springbootcamp.mainfinalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootcamp.mainfinalproject.model.OsRequirements;
import springbootcamp.mainfinalproject.model.ProcessorRequirements;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProcessorRequirementsRepository extends JpaRepository<ProcessorRequirements, Long> {
    List<ProcessorRequirements> findAllByPlatform_PlatformId(Long platformId);
}
