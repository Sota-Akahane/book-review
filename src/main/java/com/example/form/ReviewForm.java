package com.example.form;

import jakarta.validation.constraints.NotBlank;

/**
 * レビュー追加時に使用するフォーム.
 */
public class ReviewForm {
    /** 書籍ID */
    private Integer bookId;
    /** 評価 */
    private Integer rate;
    /** レビュー内容 */
    @NotBlank(message = "レビュー内容を入力してください。")
    private String comment;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ReviewForm{" +
                "bookId=" + bookId +
                ", rate=" + rate +
                ", comment='" + comment + '\'' +
                '}';
    }
}
