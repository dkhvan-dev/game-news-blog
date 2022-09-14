package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.model.GamePlatform;
import springbootcamp.mainfinalproject.repository.GamePlatformRepository;
import springbootcamp.mainfinalproject.service.GamePlatformService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GamePlatformServiceImpl implements GamePlatformService {

    private final GamePlatformRepository gamePlatformRepository;

    @Override
    public List<GamePlatform> getAllPlatforms() {
        return gamePlatformRepository.findAllByOrderByPlatformId();
    }

    @Override
    public GamePlatform getPlatformById(Long platformId) {
        GamePlatform checkPlatform = gamePlatformRepository.findById(platformId).orElse(null);
        if (checkPlatform != null) {
            return checkPlatform;
        }
        return null;
    }
}
