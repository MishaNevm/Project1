package org.example.project1.controllers;

import org.example.project1.dao.BookDAO;
import org.example.project1.dao.PeopleDAO;
import org.example.project1.models.Human;
import org.example.project1.util.HumanEmailDatabaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleDAO peopleDAO;
    private final BookDAO bookDAO;
    private final HumanEmailDatabaseValidator humanEmailDatabaseValidator;


    @Autowired
    public PeopleController(PeopleDAO peopleDAO, BookDAO bookDAO, HumanEmailDatabaseValidator humanEmailDatabaseValidator) {
        this.peopleDAO = peopleDAO;
        this.bookDAO = bookDAO;
        this.humanEmailDatabaseValidator = humanEmailDatabaseValidator;
    }

    @GetMapping
    public String readAll(Model model) {
        model.addAttribute("people", peopleDAO.readAll());
        return "people/readAll";
    }

    @GetMapping("/{id}")
    public String readOne(@PathVariable("id") int id, Model model) {
        model.addAttribute("human", peopleDAO.readOne(id));
        model.addAttribute("books", bookDAO.readAllBooksForHuman(id));
        return "people/readOne";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("human") Human human) {
        return "people/create";
    }

    @PostMapping
    public String createPostMethod(@ModelAttribute("human") @Valid Human human, BindingResult bindingResult) {
        humanEmailDatabaseValidator.validate(human,bindingResult);
        if (bindingResult.hasErrors()) return "people/create";
        peopleDAO.create(human);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("human", peopleDAO.readOne(id));
        return "people/update";
    }

    @PatchMapping("/{id}/unAppointAllFromHuman")
    public String unAppointAllFromHuman(@PathVariable("id") int id) {
        bookDAO.unAppointAllFromHuman(id);
        return "redirect:/people/" + id;
    }

    @PatchMapping("/{id}")
    public String updatePatchMethod(@ModelAttribute("human") @Valid Human human, BindingResult bindingResult) {
        humanEmailDatabaseValidator.validate(human,bindingResult);
        if (bindingResult.hasErrors()) return "people/update";
        peopleDAO.update(human);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleDAO.delete(id);
        return "redirect:/people";
    }
}
