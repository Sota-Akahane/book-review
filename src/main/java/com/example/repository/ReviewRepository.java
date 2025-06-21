package com.example.repository;

import com.example.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    public void insert(Integer bookId, Integer rate, String comment) {
        String sql = """
                INSERT INTO reviews(book_id,rate,comment) VALUES (:bookId,:rate,:comment)
                """;

        SqlParameterSource param
                = new MapSqlParameterSource()
                .addValue("bookId", bookId)
                .addValue("rate", rate)
                .addValue("comment", comment);

        template.update(sql, param);
    }
}
