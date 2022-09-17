package springbootcamp.mainfinalproject.mapper;

import org.mapstruct.Mapper;
import springbootcamp.mainfinalproject.model.News;
import springbootcamp.mainfinalproject.model.dto.NewsDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsMapper {
    NewsDto toDto(News News);
    News toEntity(NewsDto newsDto);
    List<NewsDto> toDtoList(List<News> newsList);
    List<News> toEntityList(List<NewsDto> newsDtoList);
}
