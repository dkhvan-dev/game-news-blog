package springbootcamp.mainfinalproject.service;

import org.springframework.web.multipart.MultipartFile;
import springbootcamp.mainfinalproject.model.Game;
import springbootcamp.mainfinalproject.model.GamePlatform;
import springbootcamp.mainfinalproject.model.GameRating;
import springbootcamp.mainfinalproject.model.GameRequirements;

import java.util.List;

public interface GameService {
    List<Game> getAllGames();
    Game getGameById(Long gameId);
    List<Game> getGamesByPlatform(Long platformId);
    List<Game> getGamesByGenre(Long genreId);
    List<Game> getTop5Games();
    Game addNewGame(MultipartFile gameImage, Game game, GameRequirements gameRequirements, GameRating gameRating);
    Game editGame(MultipartFile gameImageToken, Game game, GameRequirements gameRequirements, GameRating gameRating);
    void deleteGame(Long gameId);
}
