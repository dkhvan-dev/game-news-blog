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

    @ManyToOne(fetch = FetchType.EAGER)
    private FeedbackStatus feedbackStatus;

    @PrePersist
    public void preStatus() {
        this.feedbackStatus.setFeedbackStatusId(1L);
    }
}
