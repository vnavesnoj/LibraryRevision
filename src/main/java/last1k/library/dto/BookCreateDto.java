package last1k.library.dto;

import lombok.Builder;
import lombok.Value;

// Сама сущность Book имеет NOT NULL поля author и name
// Это знаем мы. Но тот, кто пишет контроллер, может не знать,
// что чревато NullPointerException, SQLException и т.д.
// Поэтому нарабатываем привычку ставить @NonNull аннотации из библиотеки lombok
@Value
@Builder
public class BookCreateDto {

    // @NonNull
    String author;

    // @NonNull
    String name;

    Integer year;
}
