package springbootcamp.mainfinalproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "roles_color")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long roleColorId;

    @Column(name = "color")
    private String roleColor;
}
