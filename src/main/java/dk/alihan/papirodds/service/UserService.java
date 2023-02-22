package dk.alihan.papirodds.service;

import dk.alihan.papirodds.models.UserOddsDTO;
import dk.alihan.papirodds.entity.User;
import dk.alihan.papirodds.entity.UserOdds;
import dk.alihan.papirodds.repository.UserOddsRepository;
import dk.alihan.papirodds.repository.UserRepository;
import dk.alihan.papirodds.request.UserValidationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserOddsRepository userOddsRepository;

    public User validateCredentials(UserValidationRequest request) throws Exception {
        log.info("Validating User Credentials");

        Optional<User> user = userRepository.findByUsernameAndValidationCode(request.getUsername(), request.getValidationCode());
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new Exception("Not found");
        }
    }

    public List<UserOddsDTO> getOddsByUserId(Long id) throws Exception {
        log.info("Getting user odds");


        List<UserOddsDTO> dtos = new ArrayList<>();
        Optional<List<UserOdds>> odds = userOddsRepository.findAllByUserId(id);

        if (odds.isPresent()) {
            for (UserOdds userOdds : odds.get()) {
                dtos.add(new UserOddsDTO(userOdds));
            }

            return dtos;
        } else {
            throw new Exception("Could not find odds for this user " + id);
        }
    }
}
