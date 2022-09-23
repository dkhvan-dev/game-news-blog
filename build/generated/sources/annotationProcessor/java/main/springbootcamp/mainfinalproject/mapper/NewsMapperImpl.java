package springbootcamp.mainfinalproject.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import springbootcamp.mainfinalproject.model.News;
import springbootcamp.mainfinalproject.model.User;
import springbootcamp.mainfinalproject.model.dto.NewsDto;
import springbootcamp.mainfinalproject.model.dto.UserDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-23T18:03:36+0600",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.4 (Amazon.com Inc.)"
)
@Component
public class NewsMapperImpl implements NewsMapper {

    @Override
    public NewsDto toDto(News News) {
        if ( News == null ) {
            return null;
        }

        NewsDto newsDto = new NewsDto();

        newsDto.setNewsId( News.getNewsId() );
        newsDto.setNewsTitle( News.getNewsTitle() );
        newsDto.setNewsDescription( News.getNewsDescription() );
        newsDto.setNewsCreateDate( News.getNewsCreateDate() );
        newsDto.setGame( News.getGame() );
        newsDto.setAuthor( userToUserDto( News.getAuthor() ) );

        return newsDto;
    }

    @Override
    public News toEntity(NewsDto newsDto) {
        if ( newsDto == null ) {
            return null;
        }

        News news = new News();

        news.setNewsId( newsDto.getNewsId() );
        news.setNewsTitle( newsDto.getNewsTitle() );
        news.setNewsDescription( newsDto.getNewsDescription() );
        news.setNewsCreateDate( newsDto.getNewsCreateDate() );
        news.setGame( newsDto.getGame() );
        news.setAuthor( userDtoToUser( newsDto.getAuthor() ) );

        return news;
    }

    @Override
    public List<NewsDto> toDtoList(List<News> newsList) {
        if ( newsList == null ) {
            return null;
        }

        List<NewsDto> list = new ArrayList<NewsDto>( newsList.size() );
        for ( News news : newsList ) {
            list.add( toDto( news ) );
        }

        return list;
    }

    @Override
    public List<News> toEntityList(List<NewsDto> newsDtoList) {
        if ( newsDtoList == null ) {
            return null;
        }

        List<News> list = new ArrayList<News>( newsDtoList.size() );
        for ( NewsDto newsDto : newsDtoList ) {
            list.add( toEntity( newsDto ) );
        }

        return list;
    }

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUserId( user.getUserId() );
        userDto.setUserFirstName( user.getUserFirstName() );
        userDto.setUserSurname( user.getUserSurname() );
        userDto.setUserAvatar( user.getUserAvatar() );

        return userDto;
    }

    protected User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( userDto.getUserId() );
        user.setUserFirstName( userDto.getUserFirstName() );
        user.setUserSurname( userDto.getUserSurname() );
        user.setUserAvatar( userDto.getUserAvatar() );

        return user;
    }
}
