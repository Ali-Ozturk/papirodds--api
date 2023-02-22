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
@Table(name = "user_odds")
public class UserPrediction extends Auditor {
    @GeneratedValue
    @Id
    private Long id;

    @OneToOne
    private PlayerMatchThreshold odds;

    @OneToOne
    private MapMatch mapMatch;

    @OneToOne
    private User user;

    private Boolean overThreshold;
}
