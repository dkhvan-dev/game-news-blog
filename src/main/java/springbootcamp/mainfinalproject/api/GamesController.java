package springbootcamp.mainfinalproject.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootcamp.mainfinalproject.model.Game;
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

    @GetMapping("{gameId}")
    public ResponseEntity<Game> getGameById(@PathVariable(name = "gameId") Long gameId) {
        Game game = gameService.getGameById(gameId);
        if (gameId != null) {
            return new ResponseEntity<>(game, HttpStatus.OK);
        }
        return null;
    }
}
