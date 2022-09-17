package springbootcamp.mainfinalproject.model.dto;

import lombok.Getter;
import lombok.Setter;
import springbootcamp.mainfinalproject.model.ApplicationBloggerStatus;
import java.time.LocalDate;

@Getter
@Setter
public class AppFormBloggerDto {
    private Long applicationFormBloggerId;
    private String applicationFormBloggerDescription;
    private LocalDate applicationFormBloggerReceiptDate;
    private LocalDate applicationFormBloggerUpdateDate;
    private ApplicationBloggerStatus applicationBloggerStatus;
    private UserDto users;
}
