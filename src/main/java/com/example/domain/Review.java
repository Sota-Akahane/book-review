package com.example.domain;

/**
 * レビューを表すドメインクラスです.
 */
public class Review {
    /** レビューID */
    private Integer id;
    /** 本ID */
    private Integer bookId;
    /** 評価 */
    private Integer rate;
    /** レビュー内容 */
    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", rate=" + rate +
                ", comment='" + comment + '\'' +
                '}';
    }
}
