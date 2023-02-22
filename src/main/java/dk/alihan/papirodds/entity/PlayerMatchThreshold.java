package dk.alihan.papirodds.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "player_match_thresholds")
public class PlayerMatchThreshold extends Auditor {
    @GeneratedValue
    @Id
    private Long id;
    private Double threshold;

    @OneToOne(fetch = FetchType.EAGER)
    private Player player;

    @ManyToOne
    private MapMatch mapMatch;
}
