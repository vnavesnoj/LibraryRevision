package last1k.library.dto;

import lombok.Value;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class BookReadDto {

    Long id;

    String author;

    String name;

    Integer year;

    PersonReadDto person;
}
