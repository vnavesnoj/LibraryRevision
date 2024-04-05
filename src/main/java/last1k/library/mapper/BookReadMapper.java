package last1k.library.mapper;

import last1k.library.database.entity.Book;
import last1k.library.database.entity.Person;
import last1k.library.dto.BookReadDto;
import last1k.library.dto.PersonReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
@Component
public class BookReadMapper implements Mapper<Book, BookReadDto> {

    private final Mapper<Person, PersonReadDto> personReadMapper;

    @Override
    public BookReadDto map(Book object) {
        return new BookReadDto(
                object.getId(),
                object.getAuthor(),
                object.getName(),
                object.getYear(),
                object.getPerson() != null ? personReadMapper.map(object.getPerson()) : null
        );
    }
}
