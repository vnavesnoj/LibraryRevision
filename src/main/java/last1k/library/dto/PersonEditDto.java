package last1k.library.dto;

import lombok.Value;

@Value
public class PersonEditDto {

    // @NonNull from lombok
    private String fullName;
    Integer yearBirth;
}
