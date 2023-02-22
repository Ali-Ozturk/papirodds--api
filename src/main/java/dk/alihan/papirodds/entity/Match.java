package dk.alihan.papirodds.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dk.alihan.papirodds.enumtype.MapType;
import dk.alihan.papirodds.enumtype.MatchType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "matches")
public class Match extends Auditor {
    @Id
    private Long id;
    private String event;
    private String hltvUrl;

    @Enumerated(EnumType.ORDINAL)
    private MatchType matchType;

    @JsonIgnore
    private Boolean validated = false;

    private LocalDateTime startDate;

    @OneToOne(fetch = FetchType.EAGER)
    private Team teamHome;

    @OneToOne(fetch = FetchType.EAGER)
    private Team teamAway;

    @ElementCollection
    private List<MapType> maps;
}
