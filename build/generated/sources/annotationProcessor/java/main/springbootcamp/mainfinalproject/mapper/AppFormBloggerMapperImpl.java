package springbootcamp.mainfinalproject.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import springbootcamp.mainfinalproject.model.ApplicationFormBlogger;
import springbootcamp.mainfinalproject.model.User;
import springbootcamp.mainfinalproject.model.dto.AppFormBloggerDto;
import springbootcamp.mainfinalproject.model.dto.UserDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-23T18:03:36+0600",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.4 (Amazon.com Inc.)"
)
@Component
public class AppFormBloggerMapperImpl implements AppFormBloggerMapper {

    @Override
    public AppFormBloggerDto toDto(ApplicationFormBlogger applicationFormBlogger) {
        if ( applicationFormBlogger == null ) {
            return null;
        }

        AppFormBloggerDto appFormBloggerDto = new AppFormBloggerDto();

        appFormBloggerDto.setApplicationFormBloggerId( applicationFormBlogger.getApplicationFormBloggerId() );
        appFormBloggerDto.setApplicationFormBloggerDescription( applicationFormBlogger.getApplicationFormBloggerDescription() );
        appFormBloggerDto.setApplicationFormBloggerReceiptDate( applicationFormBlogger.getApplicationFormBloggerReceiptDate() );
        appFormBloggerDto.setApplicationFormBloggerUpdateDate( applicationFormBlogger.getApplicationFormBloggerUpdateDate() );
        appFormBloggerDto.setApplicationBloggerStatus( applicationFormBlogger.getApplicationBloggerStatus() );
        appFormBloggerDto.setUsers( userToUserDto( applicationFormBlogger.getUsers() ) );

        return appFormBloggerDto;
    }

    @Override
    public ApplicationFormBlogger toEntity(AppFormBloggerDto appFormBloggerDto) {
        if ( appFormBloggerDto == null ) {
            return null;
        }

        ApplicationFormBlogger applicationFormBlogger = new ApplicationFormBlogger();

        applicationFormBlogger.setApplicationFormBloggerId( appFormBloggerDto.getApplicationFormBloggerId() );
        applicationFormBlogger.setApplicationFormBloggerDescription( appFormBloggerDto.getApplicationFormBloggerDescription() );
        applicationFormBlogger.setApplicationFormBloggerReceiptDate( appFormBloggerDto.getApplicationFormBloggerReceiptDate() );
        applicationFormBlogger.setApplicationFormBloggerUpdateDate( appFormBloggerDto.getApplicationFormBloggerUpdateDate() );
        applicationFormBlogger.setApplicationBloggerStatus( appFormBloggerDto.getApplicationBloggerStatus() );
        applicationFormBlogger.setUsers( userDtoToUser( appFormBloggerDto.getUsers() ) );

        return applicationFormBlogger;
    }

    @Override
    public List<AppFormBloggerDto> toDtoList(List<ApplicationFormBlogger> applicationFormBloggerList) {
        if ( applicationFormBloggerList == null ) {
            return null;
        }

        List<AppFormBloggerDto> list = new ArrayList<AppFormBloggerDto>( applicationFormBloggerList.size() );
        for ( ApplicationFormBlogger applicationFormBlogger : applicationFormBloggerList ) {
            list.add( toDto( applicationFormBlogger ) );
        }

        return list;
    }

    @Override
    public List<ApplicationFormBlogger> toEntityList(List<AppFormBloggerDto> appFormBloggerDtoList) {
        if ( appFormBloggerDtoList == null ) {
            return null;
        }

        List<ApplicationFormBlogger> list = new ArrayList<ApplicationFormBlogger>( appFormBloggerDtoList.size() );
        for ( AppFormBloggerDto appFormBloggerDto : appFormBloggerDtoList ) {
            list.add( toEntity( appFormBloggerDto ) );
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
