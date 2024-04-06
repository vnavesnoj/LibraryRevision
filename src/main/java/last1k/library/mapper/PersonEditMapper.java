package last1k.library.mapper;

import last1k.library.database.entity.Book;
import last1k.library.database.entity.Person;
import last1k.library.database.repository.PersonRepository;
import last1k.library.dto.BookEditDto;
import last1k.library.dto.PersonEditDto;
import last1k.library.dto.PersonReadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonEditMapper implements Mapper<PersonEditDto, Person> {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person map(PersonEditDto object) {
        final var person = new Person();
        copy(object, person);
        return person;
    }

    @Override
    public Person map(PersonEditDto from, Person to) {
        copy(from, to);
        return to;
    }

    private void copy(PersonEditDto from, Person to) {
       to.setFullName(from.getFullName());
       to.setYearBirth(from.getYearBirth());
    }
}
