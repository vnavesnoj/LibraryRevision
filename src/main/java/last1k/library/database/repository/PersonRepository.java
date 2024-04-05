package last1k.library.database.repository;

import last1k.library.database.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

}
