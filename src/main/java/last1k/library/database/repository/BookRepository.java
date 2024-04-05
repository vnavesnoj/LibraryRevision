package last1k.library.database.repository;

import last1k.library.database.entity.Book;
import last1k.library.database.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByPerson(Person person);
}
