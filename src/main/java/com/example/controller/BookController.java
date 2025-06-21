package com.example.controller;

import com.example.domain.Book;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("")
    public String toBookList(Model model) {
        List<Book> bookList = bookService.showBookList();
        model.addAttribute("bookList", bookList);

        return "book_list";
    }

    @GetMapping("/showBookList")
    public String showBookList(Model model) {
        return toBookList(model);
    }

}
