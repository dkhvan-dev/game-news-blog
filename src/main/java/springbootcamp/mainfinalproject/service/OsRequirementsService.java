package springbootcamp.mainfinalproject.service;

import springbootcamp.mainfinalproject.model.OsRequirements;

public interface OsRequirementsService {
    OsRequirements getOsRequirementByPlatformAndGame(Long gameId, Long platformId);
}
