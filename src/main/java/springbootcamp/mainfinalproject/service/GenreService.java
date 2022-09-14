package springbootcamp.mainfinalproject.service;

import springbootcamp.mainfinalproject.model.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAllGenres();
    Genre getGenreById(Long genreId);
}
