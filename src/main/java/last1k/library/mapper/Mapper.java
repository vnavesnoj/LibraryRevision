package last1k.library.mapper;

import org.springframework.stereotype.Component;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
public interface Mapper<F, T> {

    T map(F object);

    default T map(F from, T to) {
        return to;
    }
}
