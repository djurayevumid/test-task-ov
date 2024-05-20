package com.example.testtaskov.mapper;

import com.example.testtaskov.model.dto.GroupedBooksDto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupedBookMapper implements RowMapper<GroupedBooksDto> {
    @Override
    public GroupedBooksDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        GroupedBooksDto groupedBooksDto = new GroupedBooksDto();
        groupedBooksDto.setBookTitles(rs.getString("book_titles"));
        groupedBooksDto.setBookCount(rs.getInt("book_count"));
        groupedBooksDto.setAuthor(rs.getString("author"));
        return groupedBooksDto;
    }
}
