package springbootcamp.mainfinalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springbootcamp.mainfinalproject.model.News;
import springbootcamp.mainfinalproject.repository.NewsRepository;
import springbootcamp.mainfinalproject.service.NewsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAllByOrderByNewsCreateDate();
    }
}
