package by.vasyabylba.crud.onetomany.dto;

import lombok.Builder;
import lombok.Value;

/**
 * DTO for {@link by.vasyabylba.crud.onetomany.model.Post}
 */
@Builder
@Value
public class PostRequest {
    String title;
    String text;
}