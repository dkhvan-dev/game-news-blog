package springbootcamp.mainfinalproject.service;

import springbootcamp.mainfinalproject.model.GameRating;

import java.util.List;

public interface GameRatingService {
    List<GameRating> getAllRatings();
    GameRating getRatingById(Long gameRatingId);
}
