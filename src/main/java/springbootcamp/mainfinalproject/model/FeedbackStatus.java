package springbootcamp.mainfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "feedback_status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long feedbackStatusId;

    @Column(name = "status")
    private String feedbackStatus;

    @Column(name = "color")
    private String feedbackStatusColor;
}
