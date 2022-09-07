package springbootcamp.mainfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "games")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long gameId;

    @Column(name = "name")
    private String gameName;

    @Column(name = "age")
    private int gameAge;

    @Column(name = "image")
    private String gameImage;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<GamePlatform> platform;
}
