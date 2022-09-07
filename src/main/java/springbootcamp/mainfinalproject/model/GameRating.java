package springbootcamp.mainfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "game_ratings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class GameRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long gameRatingId;

    @Column(name = "gameplay")
    private Double gameRatingGameplay;

    @Column(name = "graphics")
    private Double gameRatingGraphics;

    @Column(name = "difficulty")
    private Double gameRatingDifficulty;

    @Column(name = "avg")
    private Double gameRatingAvg;
}
