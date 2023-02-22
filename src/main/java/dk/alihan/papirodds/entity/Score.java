package dk.alihan.papirodds.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "scores")
public class Score extends Auditor {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users")
    User user;

    @ManyToOne
    @JoinColumn(name = "contests")
    Contest contest;

    private Integer score;
}
