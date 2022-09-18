package springbootcamp.mainfinalproject.service;

import org.springframework.web.multipart.MultipartFile;
import springbootcamp.mainfinalproject.model.*;

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
    List<Game> getAllGamesByKeyword(String keyword);
    List<Game> getAllGamesByGenreByKeyword(Long genreId, String keyword);
    List<Game> getAllGamesByPlatformByKeyword(Long platformId, String keyword);
}
