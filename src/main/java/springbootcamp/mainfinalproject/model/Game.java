package springbootcamp.mainfinalproject.model;

import liquibase.pro.packaged.F;
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

    @Column(name = "description")
    private String gameDescription;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<GamePlatform> platform;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Genre> genres;

    @ManyToOne(fetch = FetchType.EAGER)
    private GameRequirements gameRequirement;

    @ManyToOne(fetch = FetchType.EAGER)
    private GameRating gameRatings;
}
