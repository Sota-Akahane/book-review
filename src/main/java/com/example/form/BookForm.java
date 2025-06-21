package com.example.form;

import jakarta.validation.constraints.NotBlank;

/**
 * 書籍追加時に使用するフォームクラスです.
 */
public class BookForm {
    /** 書籍タイトル */
    @NotBlank(message = "タイトルを入力してください。")
    private String title;
    /** 著者名 */
    @NotBlank(message = "タイトルを入力してください。")
    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "BookForm{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
