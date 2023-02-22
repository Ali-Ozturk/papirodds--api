package dk.alihan.papirodds.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "players")
public class Player extends Auditor {
    @Id
    @NonNull
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String nickname;
    private String avatarUrl;

    /*
    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Double threshold;

     */
}
