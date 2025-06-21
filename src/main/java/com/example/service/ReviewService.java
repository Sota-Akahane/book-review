package com.example.service;

import com.example.form.ReviewForm;
import com.example.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public void addReview(ReviewForm reviewForm) {
        Integer bookId = reviewForm.getBookId();
        Integer rate = reviewForm.getRate();
        String comment = reviewForm.getComment();

        reviewRepository.insert(bookId, rate, comment);
    }
}
