package springbootcamp.mainfinalproject.service;

import springbootcamp.mainfinalproject.model.Game;

import java.util.List;

public interface GameService {
    List<Game> getAllGames();
    Game getGameById(Long gameId);
    List<Game> getGamesByPlatform(Long platformId);
    List<Game> getGamesByGenre(Long genreId);
    List<Game> getTop5Games();
}
