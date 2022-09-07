package springbootcamp.mainfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "graphics_requirements")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GraphicsRequirements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long graphicsRequirementId;

    @Column(name = "name")
    private String graphicsRequirementName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    private GamePlatform platform;
}
