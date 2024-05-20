package com.example.testtaskov.controller;


import com.example.testtaskov.model.dto.AuthorCountResponseDto;
import com.example.testtaskov.model.dto.BookIdResponseDto;
import com.example.testtaskov.model.dto.BookRequestDto;
import com.example.testtaskov.model.dto.BookResponseDto;
import com.example.testtaskov.model.dto.GroupedBooksDto;
import com.example.testtaskov.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("book/")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @GetMapping("all")
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("all/grouped")
    public ResponseEntity<List<GroupedBooksDto>> getAllSortedByAuthor() {
        return ResponseEntity.ok(bookService.getAllBooksSorted());
    }

    @GetMapping("all/by-book-letter/{bookLetter}")
    public ResponseEntity<List<AuthorCountResponseDto>> getByCharCount(
            @PathVariable
            @Valid
            @Size(min = 1, max = 1, message = "Invalid parameter length.")
            String bookLetter) {
        return ResponseEntity.ok(bookService.getAllBooksTopAuthors(bookLetter));
    }

    @PostMapping("/create")
    public ResponseEntity<BookIdResponseDto> createBook(
            @RequestBody
            @Valid
            BookRequestDto bookRequestDto
    ) {

        return ResponseEntity.ok(bookService.addBook(bookRequestDto));
    }
}
