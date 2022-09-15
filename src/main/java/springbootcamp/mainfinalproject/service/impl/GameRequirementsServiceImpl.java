package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.model.GameRequirements;
import springbootcamp.mainfinalproject.repository.GameRequirementsRepository;
import springbootcamp.mainfinalproject.service.GameRequirementsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameRequirementsServiceImpl implements GameRequirementsService {

    private final GameRequirementsRepository gameRequirementsRepository;

    @Override
    public List<GameRequirements> getAllRequirements() {
        return gameRequirementsRepository.findAll();
    }

    @Override
    public GameRequirements getRequirementsById(Long gameRequirementId) {
        GameRequirements gameRequirements = gameRequirementsRepository.findById(gameRequirementId).orElse(null);
        if (gameRequirements != null) {
            return gameRequirements;
        }
        return null;
    }
}
