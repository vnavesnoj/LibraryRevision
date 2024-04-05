package last1k.library.service;

import last1k.library.database.entity.Book;
import last1k.library.database.repository.BookRepository;
import last1k.library.database.repository.PersonRepository;
import last1k.library.dto.*;
import last1k.library.mapper.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    private final PersonRepository personRepository;

    private final BookReadMapper bookReadMapper;

    private final BookEditMapper bookEditMapper;

    private final BookMapper bookMapper;

    private final BookCreateMapper bookCreateMapper;

    private final BookPatchMapper bookPatchMapper;


//    @Autowired
//    private BookRepository bookRepository;
//    @Autowired
//    private PersonRepository personRepository;
//    @Autowired
//    private BookReadMapper bookReadMapper;
//    @Autowired
//    private BookEditMapper bookEditMapper;
//    @Autowired
//    private BookMapper bookMapper;


//    @Autowired
//    private Mapper<Book, BookReadDto> bookReadMapper;
//    @Autowired
//    private Mapper<BookEditDto, Book> bookEditMapper;

    public List<BookReadDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookReadMapper::map)
                .toList();
    }

    public List<BookReadDto> findAllByPerson(@NonNull Long personId) {
        return personRepository.findById(personId)
                .map(bookRepository::findAllByPerson)
                .map(list -> list.stream()
                        .map(bookReadMapper::map)
                        .toList())
                .orElseThrow(() -> new IllegalArgumentException("person with id = " + personId + " not exists"));
    }

    public BookReadDto update(Long id, BookEditDto bookDto) {
        return bookRepository.findById(id)
                .map(entity -> bookEditMapper.map(bookDto, entity))
                .map(bookRepository::saveAndFlush)
                .map(bookReadMapper::map).orElseThrow(IllegalArgumentException::new);
    }

    public BookCreateDto create(BookReadDto bookReadDto) {
        return Optional.of(bookReadDto).map(bookMapper::map).map(bookRepository::save).map(bookCreateMapper::map).orElseThrow(IllegalArgumentException::new);
    }

    public BookReadDto findById(Long id) {
        return bookRepository.findById(id)
                .map(bookReadMapper::map)
                .orElseThrow(() -> new IllegalArgumentException("person with id = " + id + " not exists"));
    }

    public BookReadDto patch(Long personId, Long id, BookPatchDto bookPatchDto) {
      return Optional.of(id)
              .map(bookRepository::findById).orElseThrow(() -> new IllegalArgumentException("Пользователя под id " + id + " не существует"))
              .map(book -> bookPatchMapper.map(bookPatchDto, book))
              .map(book -> {
          book.setPerson(Optional.of(personId).map(personRepository::findById)
                  .orElseThrow(() -> new IllegalArgumentException("Пользователя под id " + personId + " не существует")).orElseThrow());
          return bookRepository.save(book);
      })
              .map(bookReadMapper::map).orElseThrow();
    }
// return Optional.of(id).map(bookRepository::findById).orElseThrow(() -> new IllegalArgumentException("Пользователя под id " + id + " не существует"))
//            .map(book -> {
//        book.setPerson(personRepository.findById(personId)
//                .orElseThrow(() -> new IllegalArgumentException("Пользователя под id " + personId + " не существует")));
//        return bookRepository.save(book);
//    })
//            .map(bookReadMapper::map).
//    orElseThrow(() -> new IllegalArgumentException("Я не знаю что ты сделал чтобы увидеть эту ошибку"));

}
