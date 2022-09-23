package springbootcamp.mainfinalproject.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import springbootcamp.mainfinalproject.model.Blog;
import springbootcamp.mainfinalproject.model.User;
import springbootcamp.mainfinalproject.model.dto.BlogDto;
import springbootcamp.mainfinalproject.model.dto.UserDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-23T18:03:36+0600",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.4 (Amazon.com Inc.)"
)
@Component
public class BlogMapperImpl implements BlogMapper {

    @Override
    public BlogDto toDto(Blog blog) {
        if ( blog == null ) {
            return null;
        }

        BlogDto blogDto = new BlogDto();

        blogDto.setBlogId( blog.getBlogId() );
        blogDto.setBlogTitle( blog.getBlogTitle() );
        blogDto.setBlogDescription( blog.getBlogDescription() );
        blogDto.setBlogCreateDate( blog.getBlogCreateDate() );
        blogDto.setBlogUpdateDate( blog.getBlogUpdateDate() );
        blogDto.setBlogStatus( blog.getBlogStatus() );
        blogDto.setGames( blog.getGames() );
        blogDto.setUsers( userToUserDto( blog.getUsers() ) );
        blogDto.setBlogImage( blog.getBlogImage() );

        return blogDto;
    }

    @Override
    public Blog toEntity(BlogDto blogDto) {
        if ( blogDto == null ) {
            return null;
        }

        Blog blog = new Blog();

        blog.setBlogId( blogDto.getBlogId() );
        blog.setBlogTitle( blogDto.getBlogTitle() );
        blog.setBlogDescription( blogDto.getBlogDescription() );
        blog.setBlogCreateDate( blogDto.getBlogCreateDate() );
        blog.setBlogUpdateDate( blogDto.getBlogUpdateDate() );
        blog.setBlogStatus( blogDto.getBlogStatus() );
        blog.setGames( blogDto.getGames() );
        blog.setUsers( userDtoToUser( blogDto.getUsers() ) );
        blog.setBlogImage( blogDto.getBlogImage() );

        return blog;
    }

    @Override
    public List<BlogDto> toDtoList(List<Blog> blogList) {
        if ( blogList == null ) {
            return null;
        }

        List<BlogDto> list = new ArrayList<BlogDto>( blogList.size() );
        for ( Blog blog : blogList ) {
            list.add( toDto( blog ) );
        }

        return list;
    }

    @Override
    public List<Blog> toEntityList(List<BlogDto> blogDtoList) {
        if ( blogDtoList == null ) {
            return null;
        }

        List<Blog> list = new ArrayList<Blog>( blogDtoList.size() );
        for ( BlogDto blogDto : blogDtoList ) {
            list.add( toEntity( blogDto ) );
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
