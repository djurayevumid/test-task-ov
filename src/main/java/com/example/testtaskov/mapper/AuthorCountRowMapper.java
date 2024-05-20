package com.example.testtaskov.mapper;

import com.example.testtaskov.model.dto.AuthorCountResponseDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;



public class AuthorCountRowMapper implements RowMapper<AuthorCountResponseDto> {
    @Override
    public AuthorCountResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        AuthorCountResponseDto authorBooks = new AuthorCountResponseDto();
        authorBooks.setAuthor(rs.getString("author"));
        authorBooks.setCharCount(rs.getInt("char_count"));
        return authorBooks;
    }
}
