package com.example.service;

import com.example.domain.Book;
import com.example.form.BookForm;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 本に関係する機能の業務処理を行うサービスクラスです.
 */
@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * 本の一覧を取得します.
     *
     * @return 本の一覧
     */
    public List<Book> showBookList() {
        return bookRepository.findAll();
    }

    /**
     * 書籍を追加します.
     *
     * @param bookForm フォーム
     */
    public void addBook(BookForm bookForm) {
        String title = bookForm.getTitle();
        String author = bookForm.getAuthor();

        bookRepository.insert(title, author);
    }

    /**
     * 主キーで一件検索をします.
     *
     * @param bookId 書籍ID
     * @return 書籍情報
     */
    public Book searchById(Integer bookId) {
        return bookRepository.findById(bookId);
    }
}
