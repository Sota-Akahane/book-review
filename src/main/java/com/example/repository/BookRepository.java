package com.example.repository;

import com.example.domain.Book;
import com.example.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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

    /**
     * 二つのテーブルを結合して処理する場合に使用するResultSetExtractor.
     * 一件検索に特化させるために、型をリストにしていない。
     */
    private static final ResultSetExtractor<Book> BOOK_RESULT_SET_EXTRACTOR
            = (rs) -> {

        Book book = null;
        while (rs.next()) {
            if (book == null) {
                book = new Book();
                book.setId(rs.getInt("b_id"));
                book.setTitle(rs.getString("b_title"));
                book.setAuthor(rs.getString("b_author"));
                book.setReviews(new ArrayList<>());
            }

            Integer reviewId = rs.getInt("r_id");
            if (reviewId != null) {
                Review review = new Review();
                review.setId(rs.getInt("r_id"));
                review.setRate(rs.getInt("r_rate"));
                review.setComment(rs.getString("r_comment"));
                book.getReviews().add(review);
            }
        }

        return book;
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

    /**
     * 書籍を追加します.
     *
     * @param title  書籍タイトル
     * @param author 著者名
     */
    public void insert(String title, String author) {
        String sql = """
                INSERT INTO books(title, author) VALUES (:title,:author)
                """;

        SqlParameterSource param
                = new MapSqlParameterSource()
                .addValue("title", title)
                .addValue("author", author);

        template.update(sql, param);
    }

    /**
     * 主キーで書籍を検索します.
     * 二つのテーブルを結合して検索するので、レビューの一覧を保持した情報を返します。
     *
     * @param id 書籍ID
     * @return レビューリストを持ったBookオブジェクト
     */
    public Book findById(Integer id) {
        String sql = """
                SELECT b.id AS b_id,b.title AS b_title,b.author AS b_author,
                 r.id AS r_id,r.rate AS r_rate,r.comment AS r_comment
                 FROM books AS b LEFT OUTER JOIN reviews AS r ON b.id=r.book_id
                 WHERE b.id=:id
                """;

        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

        return template.query(sql, param, BOOK_RESULT_SET_EXTRACTOR);
    }
}
