package springbootcamp.mainfinalproject.service;

import springbootcamp.mainfinalproject.model.GameRequirements;

import java.util.List;

public interface GameRequirementsService {
    List<GameRequirements> getAllRequirements();
    GameRequirements getRequirementsById(Long gameRequirementId);
}
