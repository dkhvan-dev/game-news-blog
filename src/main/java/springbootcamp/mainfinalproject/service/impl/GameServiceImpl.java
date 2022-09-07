package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.model.Game;
import springbootcamp.mainfinalproject.model.GamePlatform;
import springbootcamp.mainfinalproject.repository.GamePlatformRepository;
import springbootcamp.mainfinalproject.repository.GameRepository;
import springbootcamp.mainfinalproject.service.GameService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final GamePlatformRepository gamePlatformRepository;

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAllByOrderByGameId();
    }

    @Override
    public Game getGameById(Long gameId) {
        return gameRepository.findById(gameId).orElse(null);
    }

    @Override
    public List<Game> getGamesByPlatform(Long platformId) {
        GamePlatform checkPlatform = gamePlatformRepository.findById(platformId).orElse(null);
        if (checkPlatform != null) {
            return gameRepository.findAllByPlatformId(platformId);
        }
        return null;
    }
}
