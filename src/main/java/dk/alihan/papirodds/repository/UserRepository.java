package dk.alihan.papirodds.repository;

import dk.alihan.papirodds.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndValidationCode(String username, String code);
}
