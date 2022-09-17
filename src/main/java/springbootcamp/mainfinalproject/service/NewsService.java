package springbootcamp.mainfinalproject.service;

import springbootcamp.mainfinalproject.model.News;
import springbootcamp.mainfinalproject.model.dto.NewsDto;

import java.util.List;

public interface NewsService {
    List<NewsDto> getAllNews();
    NewsDto getNewsById(Long newsId);
    News addNews(News news);
    News editNews(News news, String newsCreateDate);
    void deleteNews(Long newsId);
    NewsDto getLastNews();
    NewsDto getLastNewsByPlatform(Long platformId);
    NewsDto getLastNewsByGenre(Long genreId);
    List<NewsDto> getAllNewsByGame(Long gameId);
    List<NewsDto> getAllNewsByGenre(Long genreId);
    List<NewsDto> getAllNewsByPlatform(Long platformId);
    List<NewsDto> getLatest3News();
    List<NewsDto> getLast3NewsByGame(Long gameId);
}
