package dk.alihan.papirodds.repository;

import dk.alihan.papirodds.entity.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestRepository extends JpaRepository<Contest, Long> {

    Contest findFirstByOrderById();
}
