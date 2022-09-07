package springbootcamp.mainfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "game_requirements")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class GameRequirements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long gameRequirementsId;

    @Column(name = "operation_system")
    private String gameRequirementsOS;

    @Column(name = "cpu")
    private String gameRequirementsCPU;

    @Column(name = "memory")
    private String gameRequirementsMemory;

    @Column(name = "gpu")
    private String gameRequirementsGPU;

    @Column(name = "storage")
    private String gameRequirementsStorage;
}
