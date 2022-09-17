package springbootcamp.mainfinalproject.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class UserDto {
    private Long userId;
    private String userFirstName;
    private String userSurname;
    private String userAvatar;
}
