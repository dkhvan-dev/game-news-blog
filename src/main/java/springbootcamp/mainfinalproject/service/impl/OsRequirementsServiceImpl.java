package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.model.Game;
import springbootcamp.mainfinalproject.model.GamePlatform;
import springbootcamp.mainfinalproject.model.OsRequirements;
import springbootcamp.mainfinalproject.repository.GamePlatformRepository;
import springbootcamp.mainfinalproject.repository.GameRepository;
import springbootcamp.mainfinalproject.repository.OsRequirementsRepository;
import springbootcamp.mainfinalproject.service.OsRequirementsService;

@Service
@RequiredArgsConstructor
public class OsRequirementsServiceImpl implements OsRequirementsService {
    private final OsRequirementsRepository osRequirementsRepository;
    private final GameRepository gameRepository;
    private final GamePlatformRepository gamePlatformRepository;

    @Override
    public OsRequirements getOsRequirementByPlatformAndGame(Long gameId, Long platformId) {
        Game checkGame = gameRepository.findById(gameId).orElse(null);
        GamePlatform checkPlatform = gamePlatformRepository.findById(platformId).orElse(null);

        if (checkGame != null && checkPlatform != null) {
            return osRequirementsRepository.findAllByGame_GameIdAndPlatform_PlatformId(gameId, platformId);
        }
        return null;
    }
}
