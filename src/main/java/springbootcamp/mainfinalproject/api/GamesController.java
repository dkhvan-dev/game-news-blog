package springbootcamp.mainfinalproject.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootcamp.mainfinalproject.model.Game;
import springbootcamp.mainfinalproject.model.GameRating;
import springbootcamp.mainfinalproject.model.GameRequirements;
import springbootcamp.mainfinalproject.service.GameService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/games")
@RequiredArgsConstructor
public class GamesController {
    private final GameService gameService;

    @GetMapping()
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> allGames = gameService.getAllGames();
        return new ResponseEntity<>(allGames, HttpStatus.OK);
    }

    @GetMapping("/keyword={keyword}")
    public ResponseEntity<List<Game>> getAllGamesByKeyword(@PathVariable(name = "keyword") String keyword) {
        return new ResponseEntity<>(gameService.getAllGamesByKeyword(keyword), HttpStatus.OK);
    }

    @GetMapping("{gameId}")
    public ResponseEntity<Game> getGameById(@PathVariable(name = "gameId") Long gameId) {
        return new ResponseEntity<>(gameService.getGameById(gameId), HttpStatus.OK);
    }

//    @GetMapping("/byPlatform/{platformId}")
//    public ResponseEntity<List<Game>> getGamesByPlatform(@PathVariable(name = "platformId") Long platformId) {
//        return new ResponseEntity<>(gameService.getGamesByPlatform(platformId), HttpStatus.OK);
//    }

//    @GetMapping("/byGenre/{genreId}")
//    public ResponseEntity<List<Game>> getGamesByGenre(@PathVariable(name = "genreId") Long genreId) {
//        return new ResponseEntity<>(gameService.getGamesByGenre(genreId), HttpStatus.OK);
//    }

    @GetMapping("/byGenre/{genreId}/keyword={keyword}")
    public ResponseEntity<List<Game>> getGamesByGenreByKeyword(@PathVariable(name = "genreId") Long genreId,
                                                               @PathVariable(name = "keyword") String keyword) {
        return new ResponseEntity<>(gameService.getAllGamesByGenreByKeyword(genreId, keyword), HttpStatus.OK);
    }

    @GetMapping("/byPlatform/{platformId}/keyword={keyword}")
    public ResponseEntity<List<Game>> getGamesByPlatformByKeyword(@PathVariable(name = "platformId") Long platformId,
                                                                  @PathVariable(name = "keyword") String keyword) {
        return new ResponseEntity<>(gameService.getAllGamesByPlatformByKeyword(platformId, keyword), HttpStatus.OK);
    }

    @GetMapping("/top5Games")
    public ResponseEntity<List<Game>> getTop5Games() {
        return new ResponseEntity<>(gameService.getTop5Games(), HttpStatus.OK);
    }
}
