package last1k.library.service;

import last1k.library.database.entity.Book;
import last1k.library.database.repository.BookRepository;
import last1k.library.database.repository.PersonRepository;
import last1k.library.dto.*;
import last1k.library.mapper.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
//@Transactional(readOnly = true)
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    private final PersonRepository personRepository;

    // Всегда в приоритете тип ссылки должен быть интерфейсом.
    // Все мапперы должны быть наподобии Mapper<Book, BookReadDto
    // А уже Spring сам заинжектит нужны объект.
    // private final Mapper<Book, BookReadDto> bookReadMapper;
    private final BookReadMapper bookReadMapper;

    // private final Mapper<BookEditDto, Book> bookEditMapper;
    private final BookEditMapper bookEditMapper;

    // Это здесь не нужно. Удаляем
    private final BookMapper bookMapper;

    // private final Mapper<BookCreateDto, Book> bookCreateMapper;
    private final BookCreateMapper bookCreateMapper;

    // private final Mapper<BookPatchDto, Book> bookPatchMapper;
    private final BookPatchMapper bookPatchMapper;


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

    // @Transactional
    public BookReadDto update(@NonNull Long id, @NonNull BookEditDto bookDto) {
        return bookRepository.findById(id)
                .map(entity -> bookEditMapper.map(bookDto, entity))
                .map(bookRepository::saveAndFlush)
                .map(bookReadMapper::map)
                //.orElseThrow(() -> new IllegalArgumentException("book with id = " + id + " not exists");
                .orElseThrow(IllegalArgumentException::new);
    }

    // @Transactional
    public BookReadDto create(@NonNull BookCreateDto bookCreateDto) {
        return Optional.of(bookCreateDto)
                .map(bookCreateMapper::map)
                .map(bookRepository::saveAndFlush)
                .map(bookReadMapper::map)
                //.orElseThrow();
                .orElseThrow(IllegalArgumentException::new);
    }

    // Лучше все же, чтобы возвращал Optional<BookReadDto>, ведь так гибче. Но как хочешь
    public BookReadDto findById(@NonNull Long id) {
        return bookRepository.findById(id)
                .map(bookReadMapper::map)
                .orElseThrow(() -> new IllegalArgumentException("person with id = " + id + " not exists"));
    }

    // @Transactional
    public BookReadDto patch(@NonNull Long id, @NonNull BookPatchDto bookPatchDto) {
        log.info(bookPatchDto.toString());
        return bookRepository.findById(id)
                .map(entity -> bookPatchMapper.map(bookPatchDto, entity))
                .map(bookRepository::saveAndFlush)
                .map(bookReadMapper::map)
                //.orElseThrow(() -> new IllegalArgumentException("book with id = " + id + " not exists");
                .orElseThrow(IllegalArgumentException::new);
        }
    }

