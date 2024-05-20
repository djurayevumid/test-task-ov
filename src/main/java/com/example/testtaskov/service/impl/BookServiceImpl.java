package com.example.testtaskov.service.impl;

import com.example.testtaskov.model.dto.AuthorCountResponseDto;
import com.example.testtaskov.model.dto.BookIdResponseDto;
import com.example.testtaskov.model.dto.BookRequestDto;
import com.example.testtaskov.model.dto.BookResponseDto;
import com.example.testtaskov.model.dto.GroupedBooksDto;
import com.example.testtaskov.repository.BookRepository;
import com.example.testtaskov.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BookResponseDto> getAllBooks() {
        return bookRepository.getAllBooks()
                .stream()
                .sorted(Comparator.comparing(BookResponseDto::getTitle).reversed())
                .toList();
    }

    @Override
    @Transactional
    public BookIdResponseDto addBook(BookRequestDto bookRequestDto) {

        Integer bookId = bookRepository.addBook(bookRequestDto);
        log.info("id: {}", bookId);
        return BookIdResponseDto
                .builder()
                .bookId(bookId)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupedBooksDto> getAllBooksSorted() {
        return bookRepository.getAllBooksSortedByAuthor();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorCountResponseDto> getAllBooksTopAuthors(String bookChar) {
        return bookRepository.getTopAuthorsByChar(bookChar);
    }
}
