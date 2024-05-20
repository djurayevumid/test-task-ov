package com.example.testtaskov.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupedBooksDto {
    private String author;
    private Integer bookCount;
    private String bookTitles;
}
