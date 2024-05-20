package com.example.testtaskov.repository;


import com.example.testtaskov.mapper.AuthorCountRowMapper;
import com.example.testtaskov.mapper.BookRowMapper;
import com.example.testtaskov.mapper.GroupedBookMapper;
import com.example.testtaskov.model.dto.AuthorCountResponseDto;
import com.example.testtaskov.model.dto.BookRequestDto;
import com.example.testtaskov.model.dto.BookResponseDto;
import com.example.testtaskov.model.dto.GroupedBooksDto;
import com.example.testtaskov.model.entity.BookEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Locale;

@Repository
@RequiredArgsConstructor
public class BookRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<BookResponseDto> getAllBooks() {
        String sql = """
                SELECT * FROM book_entity
                """;
        return jdbcTemplate.query(sql, new BookRowMapper());
    }

    public Integer addBook(BookRequestDto bookRequestDto) {
        String sql = """
                insert into book_entity (title, author, description) VALUES (?, ?, ?)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, bookRequestDto.getTitle());
            ps.setString(2, bookRequestDto.getAuthor());
            ps.setString(3, bookRequestDto.getDescription());
            return ps;
        }, keyHolder);
        return (Integer) keyHolder.getKeys().get("id");
    }

    public List<GroupedBooksDto> getAllBooksSortedByAuthor() {
        String sql = """
                SELECT
                     author,
                     COUNT(*) AS book_count,
                     STRING_AGG(title, ', ') AS book_titles
                 FROM book_entity
                 GROUP BY author;
                 """;
        return jdbcTemplate.query(sql, new GroupedBookMapper());
    }

    public List<AuthorCountResponseDto> getTopAuthorsByChar(String character) {
        String sql = """
                SELECT author, SUM(CHAR_LENGTH(title) - CHAR_LENGTH(REPLACE(LOWER(title), LOWER(?), ''))) AS char_count
                FROM book_entity GROUP BY author HAVING SUM(CHAR_LENGTH(title) - CHAR_LENGTH(REPLACE(LOWER(title), LOWER(?), ''))) > 0
                ORDER BY char_count DESC LIMIT 10
                """;
        return jdbcTemplate.query(sql, new AuthorCountRowMapper(), character, character);
    }
}
