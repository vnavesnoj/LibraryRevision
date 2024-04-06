package last1k.library.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BookCreateDto {

    String author;

    String name;

    Integer year;
}
