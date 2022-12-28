package org.example.project1.controllers;

import org.example.project1.dao.BookDAO;
import org.example.project1.dao.PeopleDAO;
import org.example.project1.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final PeopleDAO peopleDAO;
    private final BookDAO bookDAO;


    @Autowired
    public BookController(PeopleDAO peopleDAO, BookDAO bookDAO) {
        this.peopleDAO = peopleDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String readAll(Model model) {
        model.addAttribute("books", bookDAO.readAll());
        return "book/readAll";
    }

    @GetMapping("/free")
    public String readFreeBooks(Model model) {
        model.addAttribute("books", bookDAO.readFreeBooks());
        return "book/readFreeBooks";
    }

    @GetMapping("/{id}")
    public String readOne(@PathVariable("id") int id, Model model) {
        Book book = bookDAO.readOne(id);
        model.addAttribute("book", book);
        if (book.getHumanId() == 0) {
            model.addAttribute("people", peopleDAO.readAll());
        } else model.addAttribute("person", peopleDAO.readOne(book.getHumanId()));
        return "book/readOne";
    }

    @PatchMapping("/{id}/appoint")
    public String appoint(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        bookDAO.appoint(book);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/un_appoint")
    public String unAppoint(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        bookDAO.unAppoint(book);
        return "redirect:/books/" + id;
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("book") Book book) {
        return "book/create";
    }

    @PostMapping
    public String createPostMethod(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "book/create";
        bookDAO.create(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.readOne(id));
        return "book/update";
    }

    @PatchMapping("/{id}")
    public String updatePatchMethod(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "book/update";
        bookDAO.update(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
