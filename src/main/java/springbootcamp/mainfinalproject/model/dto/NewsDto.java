package springbootcamp.mainfinalproject.model.dto;

import lombok.Getter;
import lombok.Setter;
import springbootcamp.mainfinalproject.model.Game;
import java.time.LocalDate;

@Getter
@Setter
public class NewsDto {
    private Long newsId;
    private String newsTitle;
    private String newsDescription;
    private LocalDate newsCreateDate;
    private Game game;
    private UserDto author;
}
