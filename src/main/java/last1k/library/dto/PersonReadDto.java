package last1k.library.dto;

import lombok.NonNull;
import lombok.Value;

import java.util.List;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class PersonReadDto {

    Long id;

    String fullName;

    Integer yearBirth;
}
