package springbootcamp.mainfinalproject.service;

import springbootcamp.mainfinalproject.model.Game;

import java.util.List;

public interface GameService {
    List<Game> getAllGames();
    Game getGameById(Long gameId);
}
