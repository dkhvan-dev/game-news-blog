package springbootcamp.mainfinalproject.mapper;

import org.mapstruct.Mapper;
import springbootcamp.mainfinalproject.model.ApplicationFormBlogger;
import springbootcamp.mainfinalproject.model.dto.AppFormBloggerDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppFormBloggerMapper {
    AppFormBloggerDto toDto(ApplicationFormBlogger applicationFormBlogger);
    ApplicationFormBlogger toEntity(AppFormBloggerDto appFormBloggerDto);
    List<AppFormBloggerDto> toDtoList(List<ApplicationFormBlogger> applicationFormBloggerList);
    List<ApplicationFormBlogger> toEntityList(List<AppFormBloggerDto> appFormBloggerDtoList);
}
