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
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    private final PersonRepository personRepository;

    private final BookReadMapper bookReadMapper;

    private final BookEditMapper bookEditMapper;

    private final BookMapper bookMapper;

    private final BookCreateMapper bookCreateMapper;

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

    public BookReadDto update(@NonNull Long id, @NonNull BookEditDto bookDto) {
        return bookRepository.findById(id)
                .map(entity -> bookEditMapper.map(bookDto, entity))
                .map(bookRepository::saveAndFlush)
                .map(bookReadMapper::map).orElseThrow(IllegalArgumentException::new);
    }

    public BookReadDto create(@NonNull BookCreateDto bookCreateDto) {
        return Optional.of(bookCreateDto)
                .map(bookCreateMapper::map)
                .map(bookRepository::saveAndFlush)
                .map(bookReadMapper::map)
                .orElseThrow(IllegalArgumentException::new);
    }

    public BookReadDto findById(@NonNull Long id) {
        return bookRepository.findById(id)
                .map(bookReadMapper::map)
                .orElseThrow(() -> new IllegalArgumentException("person with id = " + id + " not exists"));
    }

    public BookReadDto patch(@NonNull Long id, @NonNull BookPatchDto bookPatchDto) {
        log.info(bookPatchDto.toString());
        return bookRepository.findById(id)
                .map(entity -> bookPatchMapper.map(bookPatchDto, entity))
                .map(bookRepository::saveAndFlush)
                .map(bookReadMapper::map).orElseThrow(IllegalArgumentException::new);
        }
    }

