package dk.alihan.papirodds.models;

import dk.alihan.papirodds.entity.UserPrediction;
import lombok.Data;

@Data
public class UserOddsDTO {
    private final Long id;
    private final Long userId;
    private final Boolean overThreshold;
    private final PlayerMatchThresholdsDTO odds;

    public UserOddsDTO(UserPrediction userPrediction) {
        this.id = userPrediction.getId();
        this.userId = userPrediction.getUser().getId();
        this.overThreshold = userPrediction.getOverThreshold();
        this.odds = new PlayerMatchThresholdsDTO(userPrediction.getOdds());
    }
}
