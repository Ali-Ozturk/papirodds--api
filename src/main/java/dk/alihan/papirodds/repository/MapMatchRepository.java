package dk.alihan.papirodds.repository;

import dk.alihan.papirodds.entity.MapMatch;
import dk.alihan.papirodds.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapMatchRepository extends JpaRepository<MapMatch, Long> {
}
