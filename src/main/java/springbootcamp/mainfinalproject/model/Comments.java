package springbootcamp.mainfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long commentId;

    @Column(name = "comment")
    private String commentText;

    @Column(name = "create_date")
    private LocalDate commentCreateDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Blog blog;

    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    @PrePersist
    public void preCreateDate() {
        this.setCommentCreateDate(LocalDate.now());
    }
}
