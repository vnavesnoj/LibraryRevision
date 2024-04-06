package last1k.library.service;

import last1k.library.database.repository.PersonRepository;
import last1k.library.dto.PersonCreateDto;
import last1k.library.dto.PersonEditDto;
import last1k.library.dto.PersonReadDto;
import last1k.library.mapper.PersonCreateMapper;
import last1k.library.mapper.PersonEditMapper;
import last1k.library.mapper.PersonMapper;
import last1k.library.mapper.PersonReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    private final PersonReadMapper personReadMapper;

    private final PersonEditMapper personEditMapper;

    private final PersonCreateMapper personCreateMapper;


    public PersonReadDto findById(Long id) {
        return personRepository.findById(id)
                .map(personReadMapper::map)
                .orElseThrow(() -> new IllegalArgumentException("person with id = " + id + " not exists"));
    }

    public List<PersonReadDto> findAll() {
        return personRepository.findAll().stream()
                .map(personReadMapper::map).toList();
    }

    public PersonReadDto update(Long id, PersonEditDto personEditDto) {
        return personRepository.findById(id).map((person) -> personEditMapper.map(personEditDto, person))
                .map(personRepository::saveAndFlush)
                .map(personReadMapper::map)
                .orElseThrow(() -> new IllegalArgumentException("this information is not correct"));
    }

    public PersonReadDto create(PersonCreateDto personCreateDto) {
        return Optional.of(personCreateDto)
                .map(personCreateMapper::map)
                .map(personRepository::save)
                .map(personReadMapper::map)
                .orElseThrow(() ->
                        new IllegalArgumentException("User under name (" + personCreateDto.getFullName() + ") already exists"));
    }

}
