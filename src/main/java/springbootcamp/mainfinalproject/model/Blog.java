package springbootcamp.mainfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "blogs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long blogId;

    @Column(name = "title")
    private String blogTitle;

    @Column(name = "description")
    private String blogDescription;

    @Column(name = "create_date")
    private LocalDate blogCreateDate;

    @Column(name = "update_date")
    private LocalDate blogUpdateDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private BlogStatus blogStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    private Game games;

    @ManyToOne(fetch = FetchType.EAGER)
    private User users;

    @Column(name = "image")
    private String blogImage;

    @PrePersist
    public void preCreateDate() {
        this.setBlogCreateDate(LocalDate.now());
    }

    @PreUpdate
    public void preUpdateDate() {
        this.setBlogUpdateDate(LocalDate.now());
    }
}
