package last1k.library.dto;

import lombok.Value;

// Сама сущность Person имеет NOT NULL поле fullName.
// Это знаем мы. Но тот, кто пишет контроллер, может не знать,
// что чревато NullPointerException, SQLException и т.д.
// Поэтому нарабатываем привычку ставить @NonNull аннотации из библиотеки lombok
@Value
public class PersonCreateDto {

    // @NonNull
    String fullName;

    Integer yearBirth;
}
