package springbootcamp.mainfinalproject.service;

import springbootcamp.mainfinalproject.model.News;

import java.util.List;

public interface NewsService {
    List<News> getAllNews();
    News getNewsById(Long newsId);
    News addNews(News news);
    News editNews(News news, String newsCreateDate);
    void deleteNews(Long newsId);
    News getLastNews();
    News getLastNewsByPlatform(Long platformId);
    News getLastNewsByGenre(Long genreId);
    List<News> getAllNewsByGame(Long gameId);
    List<News> getAllNewsByGenre(Long genreId);
    List<News> getAllNewsByPlatform(Long platformId);
    List<News> getLatest3News();
    List<News> getLast3NewsByGame(Long gameId);
}
