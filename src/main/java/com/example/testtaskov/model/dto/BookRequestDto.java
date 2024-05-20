package com.example.testtaskov.model.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDto {
    @NotNull(message = "Title is required.")
    @Size(min = 1, max = 50, message = "Invalid title length.")
    private String title;
    @NotNull(message = "Author is required.")
    @Size(min = 1, max = 100, message = "Invalid author length.")
    private String author;
    @Size(max = 200, message = "Invalid description name.")
    private String description;
}
