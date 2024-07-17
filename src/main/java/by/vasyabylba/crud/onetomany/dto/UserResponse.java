package by.vasyabylba.crud.onetomany.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * DTO for {@link by.vasyabylba.crud.onetomany.model.User}
 */
@Builder
@Value
public class UserResponse {
    Long id;
    String createdBy;
    LocalDateTime createdDate;
    String firstName;
    String lastName;
    String email;
}