package dk.alihan.papirodds.repository;

import dk.alihan.papirodds.entity.Contest;
import dk.alihan.papirodds.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
