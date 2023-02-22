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
public class MapMatch {
    @GeneratedValue
    @Id
    private Long id;
    @Enumerated(EnumType.ORDINAL)
    private MapType mapType;
    private Integer mapNumber;

    @ManyToOne
    private Match match;
}
