package dk.alihan.papirodds.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "teams")
public class Team extends Auditor {

    @Id
    private Long id;
    private String name;
    private String logoUrl;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Player> players;


}
