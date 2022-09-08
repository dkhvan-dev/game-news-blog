package springbootcamp.mainfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "feedback")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long feedbackId;

    @Column(name = "name")
    private String feedbackName;

    @Column(name = "email")
    private String feedbackEmail;

    @Column(name = "subject")
    private String feedbackSubject;

    @Column(name = "description")
    private String feedbackDescription;

    @Column(name = "receipt_date")
    private Date feedbackReceiptDate;

    @Column(name = "update_date")
    private Date feedbackUpdateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private FeedbackStatus feedbackStatus;
}
