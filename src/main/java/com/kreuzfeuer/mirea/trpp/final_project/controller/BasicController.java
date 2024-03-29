package com.kreuzfeuer.mirea.trpp.final_project.controller;

import com.kreuzfeuer.mirea.trpp.final_project.entity.Book;
import com.kreuzfeuer.mirea.trpp.final_project.entity.User;
import com.kreuzfeuer.mirea.trpp.final_project.service.impl.BookServiceImpl;
import com.kreuzfeuer.mirea.trpp.final_project.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
public class BasicController {

    private final BookServiceImpl bookService;
    private final UserServiceImpl userService;

    @GetMapping("/")
    public String startScreen() {
        return "hello";
    }

    @GetMapping("/user-note")
    public String showUserBooks(Model model, Principal principal) {

        List<Book> books = bookService.getListBookByUserLogin(principal.getName());
        model.addAttribute("books", books);

        return "user-note";
    }

    @GetMapping("/book-delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, Principal principal) {

        bookService.deleteBookByIdAndUserLogin(id, principal.getName());
        return "redirect:/user-note";
    }

    @GetMapping("/book-add")
    public String addUserBookForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "book-add";
    }

    @PostMapping("/book-add")
    public String addUserBook(@Valid @ModelAttribute("book") Book book, BindingResult errors, Model model, Principal principal) {

        if (errors.hasErrors()) {
            model.addAttribute("errorMessage", "Incorrect book information!");
            return "book-add";
        }
        if (book.getBookName().isBlank()) {
            model.addAttribute("errorMessage", "Title is blank!");
            return "book-add";
        }

        User user = userService.findByLogin(principal.getName());

        book.setUser(user);

        bookService.save(book);

        return "redirect:/user-note";
    }

    @GetMapping("/book-update/{id}")
    public String updateBookForm(@PathVariable("id") Long id, Model model, Principal principal) {
        Book book;
        try {
            book = bookService.findBookByIdAndUserLogin(id, principal.getName()).orElseThrow();
        } catch (NoSuchElementException ex) {
            return "user-note";
        }
        model.addAttribute("book", book);

        return "book-update";
    }

    @PostMapping("/book-update")
    public String updateUserBook(@Valid @ModelAttribute("book") Book book, BindingResult errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("errorMessage", "Incorrect book information!");
            return "book-update";
        }

        if (book.getBookName().isBlank()) {
            model.addAttribute("errorMessage", "Title is blank!");
            return "book-update";
        }

        bookService.save(book);

        return "redirect:/user-note";
    }

}