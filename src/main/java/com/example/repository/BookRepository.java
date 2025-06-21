package com.example.repository;

import com.example.domain.Book;
import com.example.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Book> BOOK_ROW_MAPPER = (rs, i) -> {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));

        return book;
    };

    private static final ResultSetExtractor<List<Book>> BOOK_RESULT_SET_EXTRACTOR
            = (rs) -> {

        Map<Integer, Book> bookMap = new LinkedHashMap<>();

        while (rs.next()) {
            Integer bookId = rs.getInt("b_id");
            Book book = bookMap.get(bookId);

            if (book == null) {
                book = new Book();
                book.setId(bookId);
                book.setTitle(rs.getString("b_title"));
                book.setAuthor(rs.getString("b_author"));
                book.setReviews(new ArrayList<>());

                bookMap.put(bookId, book);
            }

            Review review = new Review();
            review.setId(rs.getInt("r_id"));
            review.setBookId(rs.getInt("b_id"));
            review.setRate(rs.getInt("r_rate"));
            review.setComment(rs.getString("r_comment"));

            book.getReviews().add(review);
        }

        return new ArrayList<>(bookMap.values());
    };

    /**
     * 本を全件検索します.
     *
     * @return 本の一覧
     */
    public List<Book> findAll() {
        String sql = """
                SELECT id,title,author FROM books ORDER BY author
                """;

        return template.query(sql, BOOK_ROW_MAPPER);
    }
}
