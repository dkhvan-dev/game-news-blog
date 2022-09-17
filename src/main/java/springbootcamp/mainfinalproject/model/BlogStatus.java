package springbootcamp.mainfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "blog_status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long blogStatusId;

    @Column(name = "name")
    private String blogStatusName;
}
