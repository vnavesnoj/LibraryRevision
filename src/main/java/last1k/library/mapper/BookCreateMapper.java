package last1k.library.mapper;

import last1k.library.database.entity.Book;
import last1k.library.dto.BookCreateDto;
import org.springframework.stereotype.Component;

@Component
public class BookCreateMapper implements Mapper<BookCreateDto, Book> {

    @Override
    public Book map(BookCreateDto object) {
        return Book.builder()
                .name(object.getName())
                .year(object.getYear())
                .author(object.getAuthor())
                .build();
    }
}
