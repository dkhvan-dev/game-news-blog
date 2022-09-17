package springbootcamp.mainfinalproject.mapper;

import org.mapstruct.Mapper;
import springbootcamp.mainfinalproject.model.Blog;
import springbootcamp.mainfinalproject.model.dto.BlogDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BlogMapper {
    BlogDto toDto(Blog blog);
    Blog toEntity(BlogDto blogDto);
    List<BlogDto> toDtoList(List<Blog> blogList);
    List<Blog> toEntityList(List<BlogDto> blogDtoList);
}
