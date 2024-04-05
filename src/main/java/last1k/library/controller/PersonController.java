package last1k.library.controller;

import last1k.library.dto.PersonEditDto;
import last1k.library.dto.PersonReadDto;
import last1k.library.service.BookService;
import last1k.library.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"person"})
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private BookService bookService;


    @GetMapping("/people/new")
    public String createPersonPage() {
        return "createPerson";
    }

    @PostMapping("/people/new")
    public String createPerson(PersonReadDto personReadDto, Model model) {
        PersonReadDto person = personService.create(personReadDto);
        model.addAttribute("person", person);
        return "redirect:/people/%s/edit".formatted(person.getId());
    }

    @GetMapping("/people/{id}/edit")
    public String editPersonPage(@PathVariable Long id, Model model) {
        model.addAttribute("person", personService.findById(id));
        return "editPerson";
    }

    @PostMapping("/people/{name}/edit")
    public String editPage(Long id, PersonEditDto personEditDto, Model model) {
        model.addAttribute("person", personService.update(id, personEditDto));
        return "redirect:/people/%s/edit".formatted(id);
    }

    @GetMapping("/people")
    public String peoplePage(Model model) {
        model.addAttribute("people", personService.findAll());
        return "people";
    }

    @GetMapping("people/{id}")
    public String personPage(@PathVariable Long id, Model model) {
        model.addAttribute("person", personService.findById(id));
        model.addAttribute("books", bookService.findAllByPerson(id));
        return "person";
    }
}


























