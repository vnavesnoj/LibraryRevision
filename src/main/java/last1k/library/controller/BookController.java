package last1k.library.controller;

import last1k.library.dto.BookEditDto;
import last1k.library.dto.BookReadDto;
import last1k.library.service.BookService;
import last1k.library.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"book"})
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private PersonService personService;

    @GetMapping("/books/new")
    public String createBookPage() {
        return "createBook";
    }

    @PostMapping("/books/new")
    public String createBook(Model model, BookReadDto bookReadDto) {
        BookReadDto book = bookService.create(bookReadDto);
        model.addAttribute("book", book);
        return "redirect:/books/%s/edit".formatted(book.getId());
    }

    @GetMapping("/books/{id}/edit")
    public String editBookPage(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "editBook";
    }
    @PostMapping("/books/{id}/edit")
    public String editBook(BookEditDto bookEditDto, @PathVariable Long id, Model model) {
        BookReadDto book = bookService.update(id, bookEditDto);
        model.addAttribute("book", book);
        return "/books/%s/edit".formatted(book.getId());
    }
    @GetMapping("/books/{id}")
    public String bookPage(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("people", personService.findAll());
        return "book";
    }

    @PostMapping("/books/{id}")
    public String book(Long personId, Model model, @PathVariable Long id) {
        model.addAttribute("book", bookService.setPerson(personId, id));
        return "redirect:/books/" + id;
    }

}
