package dk.alihan.papirodds.entity;

import dk.alihan.papirodds.enumtype.MapType;
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
@Table(name = "map_matches")
public class MapMatch extends Auditor {
    @GeneratedValue
    @Id
    private Long id;
    @Enumerated(EnumType.ORDINAL)
    private MapType mapType;
    private Integer mapNumber;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;
}
