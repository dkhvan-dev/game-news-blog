package springbootcamp.mainfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "application_form_blogger")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationFormBlogger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long applicationFormBloggerId;

    @Column(name = "description")
    private String applicationFormBloggerDescription;

    @Column(name = "receipt_date")
    private LocalDate applicationFormBloggerReceiptDate;

    @Column(name = "update_date")
    private LocalDate applicationFormBloggerUpdateDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private ApplicationBloggerStatus applicationBloggerStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    private User users;
}
