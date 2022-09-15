package springbootcamp.mainfinalproject.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootcamp.mainfinalproject.model.Genre;
import springbootcamp.mainfinalproject.service.GenreService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenresController {
    private final GenreService genreService;

    @GetMapping()
    public ResponseEntity<List<Genre>> getAllGenres() {
        return new ResponseEntity<>(genreService.getAllGenres(), HttpStatus.OK);
    }

    @GetMapping("{genreId}")
    public ResponseEntity<Genre> getGenreById(@PathVariable(name = "genreId") Long genreId) {
        return new ResponseEntity<>(genreService.getGenreById(genreId), HttpStatus.OK);
    }
}
