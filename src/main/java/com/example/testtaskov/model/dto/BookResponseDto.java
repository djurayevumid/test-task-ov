package com.example.testtaskov.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {

    private Integer id;
    private String title;
    private String author;
    private String description;

}
