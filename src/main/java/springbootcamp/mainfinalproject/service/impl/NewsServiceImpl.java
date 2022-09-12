package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.model.Game;
import springbootcamp.mainfinalproject.model.News;
import springbootcamp.mainfinalproject.repository.NewsRepository;
import springbootcamp.mainfinalproject.service.GameService;
import springbootcamp.mainfinalproject.service.NewsService;
import springbootcamp.mainfinalproject.service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final UserService userService;
    private final GameService gameService;

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAllByOrderByNewsCreateDate();
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
            newsRepository.save(news);
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
}
