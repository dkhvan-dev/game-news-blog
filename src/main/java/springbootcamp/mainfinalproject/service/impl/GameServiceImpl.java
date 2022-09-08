package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.model.Game;
import springbootcamp.mainfinalproject.model.GamePlatform;
import springbootcamp.mainfinalproject.model.Genre;
import springbootcamp.mainfinalproject.repository.GamePlatformRepository;
import springbootcamp.mainfinalproject.repository.GameRepository;
import springbootcamp.mainfinalproject.repository.GenreRepository;
import springbootcamp.mainfinalproject.service.GameService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final GamePlatformRepository gamePlatformRepository;
    private final GenreRepository genreRepository;

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

    @Override
    public List<Game> getGamesByGenre(Long genreId) {
        Genre checkGenre = genreRepository.findById(genreId).orElse(null);
        if (checkGenre != null) {
            return gameRepository.findAllByGenreId(genreId);
        }
        return null;
    }

    @Override
    public List<Game> getTop5Games() {
        return gameRepository.searchTop5Games();
    }
}
