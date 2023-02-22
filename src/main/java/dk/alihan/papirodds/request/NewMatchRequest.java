package dk.alihan.papirodds.request;

import dk.alihan.papirodds.entity.Match;
import dk.alihan.papirodds.models.MapsAndThresholds;
import lombok.Data;

import java.util.List;

@Data
public class NewMatchRequest {
    private Match match;
    private List<MapsAndThresholds> mapsAndThresholds;
}
