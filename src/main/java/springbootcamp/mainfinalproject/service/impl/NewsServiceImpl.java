package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.model.Game;
import springbootcamp.mainfinalproject.model.GamePlatform;
import springbootcamp.mainfinalproject.model.Genre;
import springbootcamp.mainfinalproject.model.News;
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

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAllByOrderByNewsCreateDateDesc();
    }

    @Override
    public News getNewsById(Long newsId) {
        News news = newsRepository.findById(newsId).orElse(null);
        if (news != null) {
            return news;
        }
        return null;
    }

    @Override
    public News addNews(News news) {
        if (news.getNewsId() == null) {
            news.setAuthor(userService.getCurrentUser());
            news.setNewsCreateDate(LocalDate.now());
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
    public News getLastNews() {
        return newsRepository.findLastNews();
    }

    @Override
    public News getLastNewsByPlatform(Long platformId) {
        GamePlatform checkPlatform = gamePlatformService.getPlatformById(platformId);
        if (checkPlatform != null) {
            return newsRepository.findLastNewsByPlatform(platformId);
        }
        return null;
    }

    @Override
    public News getLastNewsByGenre(Long genreId) {
        Genre checkGenre = genreService.getGenreById(genreId);
        if (checkGenre != null) {
            return newsRepository.findLastNewsByGenre(genreId);
        }
        return null;
    }

    @Override
    public List<News> getAllNewsByGame(Long gameId) {
        Game checkGame = gameService.getGameById(gameId);
        if (checkGame != null) {
            return newsRepository.findAllByGameGameId(gameId);
        }
        return null;
    }

    @Override
    public List<News> getAllNewsByPlatform(Long platformId) {
        GamePlatform checkPlatform = gamePlatformService.getPlatformById(platformId);
        if (checkPlatform != null) {
            return newsRepository.findAllNewsByPlatform(platformId);
        }
        return null;
    }

    @Override
    public List<News> getAllNewsByGenre(Long genreId) {
        Genre checkGenre = genreService.getGenreById(genreId);
        if (checkGenre != null) {
            return newsRepository.findAllNewsByGenre(genreId);
        }
        return null;
    }

    @Override
    public List<News> getLatest3News() {
        return newsRepository.findLatest3News();
    }

    @Override
    public List<News> getLast3NewsByGame(Long gameId) {
        Game checkGame = gameService.getGameById(gameId);
        if (checkGame != null) {
            return newsRepository.findLatest3NewsByGame(gameId);
        }
        return null;
    }
}
