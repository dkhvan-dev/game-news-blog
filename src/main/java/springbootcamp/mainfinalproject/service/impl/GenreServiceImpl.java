package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.model.Genre;
import springbootcamp.mainfinalproject.repository.GenreRepository;
import springbootcamp.mainfinalproject.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAllByOrderByGenreId();
    }

    @Override
    public Genre getGenreById(Long genreId) {
        Genre checkGenre = genreRepository.findById(genreId).orElse(null);
        if (checkGenre != null) {
            return checkGenre;
        }
        return null;
    }
}
