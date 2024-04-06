package last1k.library.mapper;

import last1k.library.database.entity.Book;
import last1k.library.dto.BookReadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Этот класс ты ни где не используешь. Удаляем
@Component
public class BookMapper implements Mapper<BookReadDto, Book> {
    @Autowired
    private PersonMapper personMapper;
    @Override
    public Book map(BookReadDto object) {
        return Book.builder()
                .author(object.getAuthor())
                .id(object.getId())
                .name(object.getName())
                .year(object.getYear())
                .person(personMapper.map(object.getPerson()))
                .build();
    }
}
