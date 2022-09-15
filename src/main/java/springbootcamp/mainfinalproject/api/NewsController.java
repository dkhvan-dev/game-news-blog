package springbootcamp.mainfinalproject.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootcamp.mainfinalproject.model.News;
import springbootcamp.mainfinalproject.service.NewsService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping()
    public ResponseEntity<List<News>> getAllNews() {
        List<News> allNews = newsService.getAllNews();
        return new ResponseEntity<>(allNews, HttpStatus.OK);
    }

    @GetMapping("{newsId}")
    public ResponseEntity<News> getNewsById(@PathVariable(name = "newsId") Long newsId) {
        News news = newsService.getNewsById(newsId);

        return new ResponseEntity<>(news, HttpStatus.OK);
    }
}
