package dk.alihan.papirodds.request;

import lombok.Data;

@Data
public class UserValidationRequest {
    private String username;
    private String validationCode;
}
