package dk.alihan.papirodds.models;

import lombok.Data;

@Data
public class MapsAndThresholds {
    private Integer mapNumber;
    private String mapName;
    private Long playerId;
    private Double threshold;
}