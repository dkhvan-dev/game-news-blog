package springbootcamp.mainfinalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootcamp.mainfinalproject.model.OsRequirements;
import springbootcamp.mainfinalproject.model.StorageRequirements;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface StorageRequirementsRepository extends JpaRepository<StorageRequirements, Long> {
    List<StorageRequirements> findAllByPlatform_PlatformId(Long platformId);
}
