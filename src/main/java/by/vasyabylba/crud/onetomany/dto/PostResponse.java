package by.vasyabylba.crud.onetomany.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * DTO for {@link by.vasyabylba.crud.onetomany.model.Post}
 */
@Builder
@Value
public class PostResponse {
    Long id;
    String title;
    String text;
    LocalDateTime publishedAt;
    Long userId;
}