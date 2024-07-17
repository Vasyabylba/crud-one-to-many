package by.vasyabylba.crud.onetomany.exception;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Builder
@Value
public class ErrorMessage {
    int statusCode;
    LocalDateTime timestamp;
    String message;
}
