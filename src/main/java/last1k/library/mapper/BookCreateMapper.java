package last1k.library.mapper;

import last1k.library.database.entity.Book;
import last1k.library.dto.BookCreateDto;
import org.springframework.stereotype.Component;

@Component
public class BookCreateMapper implements Mapper<Book, BookCreateDto> {
    @Override
    public BookCreateDto map(Book object) {
        return BookCreateDto.builder()
                .name(object.getName())
                .year(object.getYear())
                .id(object.getId())
                .author(object.getAuthor())
                .build();
    }
}
