package springbootcamp.mainfinalproject.model.dto;

import lombok.Getter;
import lombok.Setter;
import springbootcamp.mainfinalproject.model.BlogStatus;
import springbootcamp.mainfinalproject.model.Game;

import java.time.LocalDate;

@Getter
@Setter
public class BlogDto {
    private Long blogId;
    private String blogTitle;
    private String blogDescription;
    private LocalDate blogCreateDate;
    private LocalDate blogUpdateDate;
    private BlogStatus blogStatus;
    private Game games;
    private UserDto users;
    private String blogImage;
}
