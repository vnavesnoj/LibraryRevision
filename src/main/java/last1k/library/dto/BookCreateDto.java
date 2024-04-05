package last1k.library.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BookCreateDto {
    Long id;

    String author;

    String name;

    Integer year;
}
