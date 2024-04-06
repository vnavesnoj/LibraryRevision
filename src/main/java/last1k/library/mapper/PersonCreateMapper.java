package last1k.library.mapper;

import last1k.library.database.entity.Person;
import last1k.library.dto.PersonCreateDto;

public class PersonCreateMapper implements Mapper<PersonCreateDto, Person> {
    @Override
    public Person map(PersonCreateDto object) {
        return Person.builder()
                .fullName(object.getFullName())
                .yearBirth(object.getYearBirth())
                .build();
    }
}
