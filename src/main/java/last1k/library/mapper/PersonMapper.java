package last1k.library.mapper;

import last1k.library.database.entity.Person;
import last1k.library.dto.BookReadDto;
import last1k.library.dto.PersonReadDto;
import org.springframework.stereotype.Component;

// Класс нигде не используется. Удаляем.
@Component
public class PersonMapper implements Mapper<PersonReadDto, Person> {
    @Override
    public Person map(PersonReadDto object) {
        if (object != null) {
            return Person.builder()
                    .fullName(object.getFullName())
                    .yearBirth(object.getYearBirth())
                    .id(object.getId())
                    .build();
        } else {
            return null;
        }

    }
}
