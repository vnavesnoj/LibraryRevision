package last1k.library.mapper;

import last1k.library.database.entity.Book;
import last1k.library.database.repository.BookRepository;
import last1k.library.dto.BookPatchDto;
import last1k.library.dto.BookReadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class BookPatchMapper implements Mapper<BookPatchDto, Book> {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book map(BookPatchDto object) {
        return Book.builder()
                .author(object.getAuthor())

                .build();
    }

    @Override
    public Book map(BookPatchDto from, Book to) {
        if(from != null) {
            if (from.getYear() != null) {
                to.setYear(from.getYear());
            }
            if (from.getAuthor() != null) {
                to.setAuthor(from.getAuthor());
            }
            if (from.getName() != null) {
                to.setName(from.getName());
            }
            return bookRepository.save(to);
        } else {
            return to;
        }
    }
}
