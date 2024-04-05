package last1k.library.service;

import last1k.library.database.repository.PersonRepository;
import last1k.library.dto.PersonEditDto;
import last1k.library.dto.PersonReadDto;
import last1k.library.mapper.PersonEditMapper;
import last1k.library.mapper.PersonMapper;
import last1k.library.mapper.PersonReadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    //findById, findAll, update, create
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonReadMapper personReadMapper;
    @Autowired
    private PersonEditMapper personEditMapper;
    @Autowired
    private PersonMapper personMapper;

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
                .map(personReadMapper::map)
                .orElseThrow(() -> new IllegalArgumentException("Я хуй знает что не так, но что-то не так"));
    }

    public PersonReadDto create(PersonReadDto personReadDto) {
        return Optional.of(personReadDto)
                .map(personMapper::map)
                .map(personRepository::save)
                .map(personReadMapper::map)
                .orElseThrow(() -> new IllegalArgumentException("попробуй угадай"));

    }

}
