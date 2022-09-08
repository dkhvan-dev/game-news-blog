package springbootcamp.mainfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private String blogCreateDate;

    @Column(name = "update_date")
    private String blogUpdateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Game games;

    @ManyToOne(fetch = FetchType.LAZY)
    private User users;

    @Column(name = "image")
    private String blogImage;
}
