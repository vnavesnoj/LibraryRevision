package last1k.library.mapper;

import last1k.library.database.entity.Book;
import last1k.library.database.repository.BookRepository;
import last1k.library.dto.BookEditDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
public class BookEditMapper implements Mapper<BookEditDto, Book> {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book map(BookEditDto object) {
        final var book = new Book();
        copy(object, book);
        return book;
    }

    @Override
    public Book map(BookEditDto from, Book to) {
        copy(from, to);
        return to;
    }

    private void copy(BookEditDto from, Book to) {
        to.setAuthor(from.getAuthor());
        to.setName(from.getName());
        to.setYear(from.getYear());
        bookRepository.save(to);
    }
}
