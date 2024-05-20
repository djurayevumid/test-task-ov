package com.example.testtaskov.service;

import com.example.testtaskov.model.dto.AuthorCountResponseDto;
import com.example.testtaskov.model.dto.BookIdResponseDto;
import com.example.testtaskov.model.dto.BookRequestDto;
import com.example.testtaskov.model.dto.BookResponseDto;
import com.example.testtaskov.model.dto.GroupedBooksDto;

import java.util.List;

public interface BookService {

    List<BookResponseDto> getAllBooks();
    BookIdResponseDto addBook(BookRequestDto bookRequestDto);
    List<GroupedBooksDto> getAllBooksSorted();
    List<AuthorCountResponseDto> getAllBooksTopAuthors(String bookChar);

}
