package last1k.library.mapper;

import last1k.library.database.entity.Person;
import last1k.library.dto.PersonReadDto;
import org.springframework.stereotype.Component;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
public class PersonReadMapper implements Mapper<Person, PersonReadDto> {

    @Override
    public PersonReadDto map(Person object) {
        if (object != null) {
            return new PersonReadDto(
                    object.getId(),
                    object.getFullName(),
                    object.getYearBirth()
            );
        } else {
            return null;
        }

    }
}
