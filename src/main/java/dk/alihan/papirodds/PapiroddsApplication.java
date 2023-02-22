package dk.alihan.papirodds;

import dk.alihan.papirodds.entity.*;
import dk.alihan.papirodds.enumtype.MatchType;
import dk.alihan.papirodds.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimeZone;

@SpringBootApplication
@EnableJpaAuditing
// @EnableScheduling
@EnableCaching
public class PapiroddsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PapiroddsApplication.class, args);
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager();
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+0100"));
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("PUT", "GET", "DELETE", "POST", "PATCH");
            }
        };
    }

    // Initialized Data
    @Bean
    CommandLineRunner commandLineRunner(UserOddsRepository userOddsRepository, PlayerMatchThresholdRepository playerMatchThresholdRepository, MatchRepository matchRepository, TeamRepository teamRepository, UserRepository userRepository, ContestRepository contestRepository, ScoreRepository scoreRepository, PlayerRepository playerRepository) {
        return args -> {
            /*
            // Create User
            User user = new User(null, "Ali", "rec0il", "1234");
            userRepository.save(user);
             */

            /*
            // Players
            Player playerOne = new Player(19230L, "Ilya Osipov", "m0NESY");
            Player playerTwo = new Player(3741L, "Nikola Kovač", "NiKo");
            Player playerThree = new Player(4679L, "Justin Savage", "jks");
            Player playerFour = new Player(3972L, "Nemanja 'huNter-' Kovač", "huNter-");

            ArrayList<Player> playersTeamOne = new ArrayList<>() {
                {
                    add(playerOne);
                    add(playerTwo);
                }
            };
            ArrayList<Player> playersTeamTwo = new ArrayList<>() {
                {
                    add(playerThree);
                    add(playerFour);
                }
            };

            ArrayList<Player> allPlayers = new ArrayList<>() {
                {
                    add(playerOne);
                    add(playerTwo);
                    add(playerThree);
                    add(playerFour);
                }
            };

            playerRepository.saveAll(allPlayers);


            // Teams
            Team teamOne = new Team(1L, "G2", "https://images-platform.99static.com//L2ISiVekPXv0yQJfAPXE1jEONgo=/362x0:1378x1016/fit-in/500x500/99designs-contests-attachments/62/62739/attachment_62739704", playersTeamOne);
            Team teamTwo = new Team(2L, "NAVI", "https://images-platform.99static.com//L2ISiVekPXv0yQJfAPXE1jEONgo=/362x0:1378x1016/fit-in/500x500/99designs-contests-attachments/62/62739/attachment_62739704", playersTeamTwo);

            ArrayList<Team> allTeams = new ArrayList<>() {
                {
                    add(teamOne);
                    add(teamTwo);
                }
            };

            teamRepository.saveAll(allTeams);

            // Match
            Match match = new Match(23523L, "IEM Katowice 2023", "http://url.dk", MatchType.BO1, false, LocalDateTime.now().minusDays(3), teamOne, teamTwo);
            Match matchAfter = new Match(1234L, "Test", "http://url.dk", MatchType.BO3, false, LocalDateTime.now().plusDays(3), teamOne, teamTwo);
            ArrayList<Match> matches = new ArrayList<>() {
                {
                    add(match);
                    add(matchAfter);
                }
            };
            matchRepository.saveAll(matches);
             */

            // Contest
            /*
            Contest contest = new Contest(null, LocalDateTime.now(), LocalDateTime.now().plusWeeks(1));
            contestRepository.save(contest);

             */

            /*
            // Create odds for each player
            PlayerMatchThreshold thresholdPlayerOne = new PlayerMatchThreshold(null, 15.5, playerOne, match);
            PlayerMatchThreshold thresholdPlayerTwo = new PlayerMatchThreshold(null, 18.5, playerTwo, match);
            PlayerMatchThreshold thresholdPlayerThree = new PlayerMatchThreshold(null, 11.5, playerThree, match);
            PlayerMatchThreshold thresholdPlayerFour = new PlayerMatchThreshold(null, 14.5, playerFour, match);
            ArrayList<PlayerMatchThreshold> thresholds = new ArrayList<>() {
                {
                    add(thresholdPlayerOne);
                    add(thresholdPlayerTwo);
                    add(thresholdPlayerThree);
                    add(thresholdPlayerFour);
                }
            };
            playerMatchThresholdRepository.saveAll(thresholds);

            // Make user odds on one of the players
            UserOdds odds = new UserOdds(null, thresholdPlayerThree, user, true);
            userOddsRepository.save(odds);

            // Scores
            Score score = new Score(null, user, contest, 5);
            Score scoreTwo = new Score(null, user, contest, 10);
            Score scoreThree = new Score(null, user, contest, 7);
            scoreRepository.saveAll(Arrays.asList(score, scoreTwo, scoreThree));
             */

            /*
            // Test webclient functionality
            WebClient webClient = WebClient.create("http://localhost:5000");
            webClient.get().uri("/matches/")
                    .retrieve()
                    .bodyToMono(String.class)
                    .subscribe(System.out::println);
             */
        };
    }
}
