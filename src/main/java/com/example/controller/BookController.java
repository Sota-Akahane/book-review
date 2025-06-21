package com.example.controller;

import com.example.domain.Book;
import com.example.form.BookForm;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/showBookList")
    public String showBookList(Model model) {
        List<Book> bookList = bookService.showBookList();
        model.addAttribute("bookList", bookList);

        return "book_list";
    }

    @GetMapping("/toBookForm")
    public String toBookForm(BookForm bookForm) {
        return "book_form";
    }

    @PostMapping("/addBook")
    public String addBook(@Validated BookForm bookForm,
                          BindingResult result) {
        if(result.hasErrors()) {
            return "book_form";
        }
        
        bookService.addBook(bookForm);

        return "redirect:/showBookList";
    }

}
