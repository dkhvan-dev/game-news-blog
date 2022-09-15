package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.model.GameRating;
import springbootcamp.mainfinalproject.repository.GameRatingRepository;
import springbootcamp.mainfinalproject.service.GameRatingService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameRatingServiceImpl implements GameRatingService {

    private final GameRatingRepository gameRatingRepository;

    @Override
    public List<GameRating> getAllRatings() {
        return gameRatingRepository.findAll();
    }

    @Override
    public GameRating getRatingById(Long gameRatingId) {
        GameRating gameRating = gameRatingRepository.findById(gameRatingId).orElse(null);
        if (gameRating != null) {
            return gameRating;
        }
        return null;
    }
}
