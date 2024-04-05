package last1k.library.dto;

import lombok.NonNull;
import lombok.Value;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class BookEditDto {

    @NonNull
    String author;

    @NonNull
    String name;

    Integer year;
}
