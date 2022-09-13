package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import springbootcamp.mainfinalproject.model.*;
import springbootcamp.mainfinalproject.repository.*;
import springbootcamp.mainfinalproject.service.FileUploadService;
import springbootcamp.mainfinalproject.service.GameService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final GamePlatformRepository gamePlatformRepository;
    private final GenreRepository genreRepository;
    private final FileUploadService fileUploadService;
    private final GameRatingRepository gameRatingRepository;
    private final GameRequirementsRepository gameRequirementsRepository;
    private final BlogRepository blogRepository;

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

    @Override
    public Game addNewGame(MultipartFile gameImage, Game game, GameRequirements gameRequirements, GameRating gameRating) {
        if (game != null && gameRequirements != null && gameRating != null) {
            fileUploadService.uploadGameImage(gameImage, game);
            gameRequirementsRepository.save(gameRequirements);
            gameRatingRepository.save(gameRating);
            game.setGameRequirement(gameRequirements);
            game.setGameRatings(gameRating);
            return gameRepository.save(game);
        }
        return null;
    }

    @Override
    public Game editGame(MultipartFile gameImageToken, Game game, GameRequirements gameRequirements, GameRating gameRating) {
        if (game != null && gameRequirements != null && gameRating != null) {
            fileUploadService.uploadGameImage(gameImageToken, game);
            gameRequirementsRepository.save(gameRequirements);
            gameRatingRepository.save(gameRating);
            game.setGameRequirement(gameRequirements);
            game.setGameRatings(gameRating);
            return gameRepository.save(game);
        }
        return null;
    }

    @Override
    public void deleteGame(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow();
        if (game != null) {
            gameRepository.deleteById(gameId);
            if (game.getGameRequirement() != null) {
                gameRequirementsRepository.deleteById(game.getGameRequirement().getGameRequirementsId());
            }
            if (game.getGameRatings() != null) {
                gameRatingRepository.deleteById(game.getGameRatings().getGameRatingId());
            }
            List<Blog> blogs = blogRepository.findAllByGames_GameId(game.getGameId());
            blogRepository.deleteAll(blogs);
        }
    }
}
