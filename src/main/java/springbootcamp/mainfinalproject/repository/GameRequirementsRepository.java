package springbootcamp.mainfinalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootcamp.mainfinalproject.model.GameRequirements;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface GameRequirementsRepository extends JpaRepository<GameRequirements, Long> {

}
