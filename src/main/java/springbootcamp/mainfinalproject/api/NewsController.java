package springbootcamp.mainfinalproject.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootcamp.mainfinalproject.model.dto.NewsDto;
import springbootcamp.mainfinalproject.service.NewsService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping()
    public ResponseEntity<List<NewsDto>> getAllNews() {
        return new ResponseEntity<>(newsService.getAllNews(), HttpStatus.OK);
    }

    @GetMapping("{newsId}")
    public ResponseEntity<NewsDto> getNewsById(@PathVariable(name = "newsId") Long newsId) {
        return new ResponseEntity<>(newsService.getNewsById(newsId), HttpStatus.OK);
    }

    @GetMapping("/byGame/{gameId}")
    public ResponseEntity<List<NewsDto>> getAllNewsByGame(@PathVariable(name = "gameId") Long gameId) {
        return new ResponseEntity<>(newsService.getAllNewsByGame(gameId), HttpStatus.OK);
    }

    @GetMapping("/byPlatform/{platformId}")
    public ResponseEntity<List<NewsDto>> getAllNewsByPlatform(@PathVariable(name = "platformId") Long platformId) {
        return new ResponseEntity<>(newsService.getAllNewsByPlatform(platformId), HttpStatus.OK);
    }

    @GetMapping("/byGenre/{genreId}")
    public ResponseEntity<List<NewsDto>> getAllNewsByGenre(@PathVariable(name = "genreId") Long genreId) {
        return new ResponseEntity<>(newsService.getAllNewsByGenre(genreId), HttpStatus.OK);
    }

    @GetMapping("/lastNews")
    public ResponseEntity<NewsDto> getLastNews() {
        return new ResponseEntity<>(newsService.getLastNews(), HttpStatus.OK);
    }

    @GetMapping("/lastNewsByGenre/{genreId}")
    public ResponseEntity<NewsDto> getLastNewsByGenre(@PathVariable(name = "genreId") Long genreId) {
        return new ResponseEntity<>(newsService.getLastNewsByGenre(genreId), HttpStatus.OK);
    }

    @GetMapping("/lastNewsByPlatform/{platformId}")
    public ResponseEntity<NewsDto> getLastNewsByPlatform(@PathVariable(name = "platformId") Long platformId) {
        return new ResponseEntity<>(newsService.getLastNewsByPlatform(platformId), HttpStatus.OK);
    }

    @GetMapping("/last3News")
    public ResponseEntity<List<NewsDto>> getLast3News() {
        return new ResponseEntity<>(newsService.getLatest3News(), HttpStatus.OK);
    }

    @GetMapping("/last3NewsByGame/{gameId}")
    public ResponseEntity<List<NewsDto>> getLast3NewsByGame(@PathVariable(name = "gameId") Long gameId) {
        return new ResponseEntity<>(newsService.getLast3NewsByGame(gameId), HttpStatus.OK);
    }
}
