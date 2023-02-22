package dk.alihan.papirodds.repository;

import dk.alihan.papirodds.entity.Contest;
import dk.alihan.papirodds.entity.PlayerMatchThreshold;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerMatchThresholdRepository extends JpaRepository<PlayerMatchThreshold, Long> {
    List<PlayerMatchThreshold> findAllByMatchId(Long id);
}
