package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.mapper.NewsMapper;
import springbootcamp.mainfinalproject.model.Game;
import springbootcamp.mainfinalproject.model.GamePlatform;
import springbootcamp.mainfinalproject.model.Genre;
import springbootcamp.mainfinalproject.model.News;
import springbootcamp.mainfinalproject.model.dto.NewsDto;
import springbootcamp.mainfinalproject.repository.NewsRepository;
import springbootcamp.mainfinalproject.service.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final GamePlatformService gamePlatformService;
    private final GenreService genreService;
    private final UserService userService;
    private final GameService gameService;
    private final NewsMapper newsMapper;

    @Override
    public List<NewsDto> getAllNews() {
        return newsMapper.toDtoList(newsRepository.findAllByOrderByNewsCreateDateDesc());
    }

    @Override
    public NewsDto getNewsById(Long newsId) {
        News news = newsRepository.findById(newsId).orElse(null);
        if (news != null) {
            return newsMapper.toDto(news);
        }
        return null;
    }

    @Override
    public News addNews(News news) {
        if (news.getNewsId() == null) {
            news.setAuthor(userService.getCurrentUser());
            return newsRepository.save(news);
        }
        return null;
    }

    @Override
    public News editNews(News news, String newsCreateDate) {
        if (news != null) {
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate datetime = LocalDate.parse(newsCreateDate, pattern);
            news.setAuthor(userService.getCurrentUser());
            news.setNewsCreateDate(datetime);
            return newsRepository.save(news);
        }
        return null;
    }

    @Override
    public void deleteNews(Long newsId) {
        News news = newsRepository.findById(newsId).orElse(null);
        if (news != null) {
            newsRepository.deleteById(newsId);
        }
    }

    @Override
    public NewsDto getLastNews() {
        return newsMapper.toDto(newsRepository.findLastNews());
    }

    @Override
    public NewsDto getLastNewsByPlatform(Long platformId) {
        GamePlatform checkPlatform = gamePlatformService.getPlatformById(platformId);
        if (checkPlatform != null) {
            return newsMapper.toDto(newsRepository.findLastNewsByPlatform(platformId));
        }
        return null;
    }

    @Override
    public NewsDto getLastNewsByGenre(Long genreId) {
        Genre checkGenre = genreService.getGenreById(genreId);
        if (checkGenre != null) {
            return newsMapper.toDto(newsRepository.findLastNewsByGenre(genreId));
        }
        return null;
    }

    @Override
    public List<NewsDto> getAllNewsByGame(Long gameId) {
        Game checkGame = gameService.getGameById(gameId);
        if (checkGame != null) {
            return newsMapper.toDtoList(newsRepository.findAllByGameGameId(gameId));
        }
        return null;
    }

    @Override
    public List<NewsDto> getAllNewsByPlatform(Long platformId) {
        GamePlatform checkPlatform = gamePlatformService.getPlatformById(platformId);
        if (checkPlatform != null) {
            return newsMapper.toDtoList(newsRepository.findAllNewsByPlatform(platformId));
        }
        return null;
    }

    @Override
    public List<NewsDto> getAllNewsByGenre(Long genreId) {
        Genre checkGenre = genreService.getGenreById(genreId);
        if (checkGenre != null) {
            return newsMapper.toDtoList(newsRepository.findAllNewsByGenre(genreId));
        }
        return null;
    }

    @Override
    public List<NewsDto> getLatest3News() {
        return newsMapper.toDtoList(newsRepository.findLatest3News());
    }

    @Override
    public List<NewsDto> getLast3NewsByGame(Long gameId) {
        Game checkGame = gameService.getGameById(gameId);
        if (checkGame != null) {
            return newsMapper.toDtoList(newsRepository.findLatest3NewsByGame(gameId));
        }
        return null;
    }
}
