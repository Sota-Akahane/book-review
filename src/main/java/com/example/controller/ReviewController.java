package com.example.controller;

import com.example.domain.Book;
import com.example.form.ReviewForm;
import com.example.service.BookService;
import com.example.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private BookService bookService;

    @PostMapping("/addReview")
    public String addReview(@Validated ReviewForm reviewForm,
                            BindingResult result,
                            Model model) {
        if (result.hasErrors()) {
            Book book = bookService.searchById(reviewForm.getBookId());
            model.addAttribute("book", book);
            model.addAttribute("bookId", book.getId());
            return "book_detail";
        }

        reviewService.addReview(reviewForm);

        return "redirect:/showBookDetail?id=" + reviewForm.getBookId();
    }
}
