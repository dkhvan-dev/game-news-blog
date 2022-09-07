package springbootcamp.mainfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "storage_requirements")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StorageRequirements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long storageRequirementId;

    @Column(name = "name")
    private String storageRequirementName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    private GamePlatform platform;
}
