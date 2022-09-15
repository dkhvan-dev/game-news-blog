package springbootcamp.mainfinalproject.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootcamp.mainfinalproject.model.GameRating;
import springbootcamp.mainfinalproject.service.GameRatingService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/gameRatings")
@RequiredArgsConstructor
public class GameRatingController {
    private final GameRatingService gameRatingService;

    @GetMapping()
    public ResponseEntity<List<GameRating>> getAllRatings() {
        return new ResponseEntity<>(gameRatingService.getAllRatings(), HttpStatus.OK);
    }

    @GetMapping("{gameRatingId}")
    public ResponseEntity<GameRating> getRatingsById(@PathVariable(name = "gameRatingId") Long gameRatingId) {
        return new ResponseEntity<>(gameRatingService.getRatingById(gameRatingId), HttpStatus.OK);
    }
}
