package springbootcamp.mainfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "application_blogger_status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationBloggerStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long applicationBloggerStatusId;

    @Column(name = "status")
    private String applicationBloggerStatus;

    @Column(name = "color")
    private String applicationBloggerStatusColor;
}
