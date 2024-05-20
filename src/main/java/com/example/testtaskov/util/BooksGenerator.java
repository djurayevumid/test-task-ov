package com.example.testtaskov.util;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BooksGenerator {
    private final JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void generateBooks() {
        String checkSql = """
                select exists(select * from book_entity)
                """;
        Boolean checkResult = jdbcTemplate.queryForObject(checkSql, Boolean.class);
        if (checkResult) {
            return;
        }
        String sql = """
                insert into book_entity (title, author, description)
                values ('Crime and Punishment', 'F. Dostoevsky', null);
                insert into book_entity (title, author, description)
                values ('Anna Karenina', 'L. Tolstoy', null);
                insert into book_entity (title, author, description)
                values ('The Brothers Karamazov', 'F. Dostoevsky', null);
                insert into book_entity (title, author, description)
                values ('War and Peace', 'L. Tolstoy', null);
                insert into book_entity (title, author, description)
                values ('Dead Souls', 'N. Gogol', null);
                """;
        jdbcTemplate
                .update(sql);
    }
}
