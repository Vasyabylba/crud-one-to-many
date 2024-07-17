package by.vasyabylba.crud.onetomany.dto;

import lombok.Builder;
import lombok.Value;

/**
 * DTO for {@link by.vasyabylba.crud.onetomany.model.User}
 */
@Builder
@Value
public class UserRequest {
    String createdBy;
    String firstName;
    String lastName;
    String email;
}