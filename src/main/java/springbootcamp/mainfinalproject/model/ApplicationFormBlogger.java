package springbootcamp.mainfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private Date applicationFormBloggerReceiptDate;

    @Column(name = "update_date")
    private Date applicationFormBloggerUpdateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private ApplicationBloggerStatus applicationBloggerStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private User users;
}
