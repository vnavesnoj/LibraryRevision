package last1k.library.mapper;

import last1k.library.database.entity.Book;
import last1k.library.database.repository.BookRepository;
import last1k.library.database.repository.PersonRepository;
import last1k.library.dto.BookPatchDto;
import last1k.library.dto.BookReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@RequiredArgsConstructor
public class BookPatchMapper implements Mapper<BookPatchDto, Book> {
    private final PersonRepository personRepository;

    // Не используем. Удаляем поле.
    private final PersonMapper personMapper;

    @Override
    public Book map(BookPatchDto object) {
        final var book = new Book();
        copy(object, book);
        return book;
    }

    @Override
    public Book map(BookPatchDto from, Book to) {
        copy(from, to);
        return to;
    }

    private void copy(BookPatchDto from, Book to) {
        if (from.getAuthor() != null) {
            to.setAuthor(from.getAuthor());
        }
        if (from.getName() != null) {
            to.setName(from.getName());
        }
        if (from.getYear() != null) {
            to.setYear(from.getYear());
        }
        if (from.getPersonId() != null) {
            // Слишком длинная строка. Делаем переносы строк
            to.setPerson(personRepository.findById(from.getPersonId()).orElseThrow(() -> new IllegalArgumentException("пользователя под id" + from.getPersonId() + " не существует")));
        }
    }
}
