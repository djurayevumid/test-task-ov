package com.example.testtaskov.mapper;

import com.example.testtaskov.model.dto.BookResponseDto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<BookResponseDto> {
    @Override
    public BookResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(rs.getInt("id"));
        bookResponseDto.setTitle(rs.getString("title"));
        bookResponseDto.setAuthor(rs.getString("author"));
        bookResponseDto.setDescription(rs.getString("description"));
        return bookResponseDto;
    }
}
