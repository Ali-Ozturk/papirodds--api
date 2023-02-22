package dk.alihan.papirodds.repository;

import dk.alihan.papirodds.entity.Player;
import dk.alihan.papirodds.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
