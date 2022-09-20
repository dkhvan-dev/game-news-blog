package springbootcamp.mainfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "news")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long newsId;

    @Column(name = "title")
    private String newsTitle;

    @Column(name = "description")
    private String newsDescription;

    @Column(name = "create_date")
    private LocalDate newsCreateDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Game game;

    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    @PrePersist
    public void preCreateDate() {
        this.setNewsCreateDate(LocalDate.now());
    }
}
