package com.example.testtaskov;

import com.example.testtaskov.faker.BookRequestFaker;
import com.example.testtaskov.model.dto.BookIdResponseDto;
import com.example.testtaskov.model.dto.BookRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookControllerTest extends TestTaskOvApplicationTests {

    @Test
    @SneakyThrows
    @Sql(value = {"/script/testGetAllBooks/Before.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/testGetAllBooks/After.sql"},
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testGetAllBooksWhenThereAreBooks() {
        mockMvc
                .perform(get("/book/all").contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isOk())

                .andExpect(jsonPath("$.[0].id", is(4)))
                .andExpect(jsonPath("$.[0].title", is("War and Peace")))
                .andExpect(jsonPath("$.[0].author", is("L. Tolstoy")))
                .andExpect(jsonPath("$.[0].description", nullValue()))

                .andExpect(jsonPath("$.[1].id", is(3)))
                .andExpect(jsonPath("$.[1].title", is("The Brothers Karamazov")))
                .andExpect(jsonPath("$.[1].author", is("F. Dostoevsky")))
                .andExpect(jsonPath("$.[1].description", nullValue()))

                .andExpect(jsonPath("$.[2].id", is(5)))
                .andExpect(jsonPath("$.[2].title", is("Dead Souls")))
                .andExpect(jsonPath("$.[2].author", is("N. Gogol")))
                .andExpect(jsonPath("$.[2].description", nullValue()))

                .andExpect(jsonPath("$.[3].id", is(1)))
                .andExpect(jsonPath("$.[3].title", is("Crime and Punishment")))
                .andExpect(jsonPath("$.[3].author", is("F. Dostoevsky")))
                .andExpect(jsonPath("$.[3].description", nullValue()))

                .andExpect(jsonPath("$.[4].id", is(2)))
                .andExpect(jsonPath("$.[4].title", is("Anna Karenina")))
                .andExpect(jsonPath("$.[4].author", is("L. Tolstoy")))
                .andExpect(jsonPath("$.[4].description", nullValue()));
    }

    @Test
    @SneakyThrows
    @Sql(value = {"/script/whenThereAreNoBooks/Before.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/whenThereAreNoBooks/After.sql"},
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testWhenThereAreNoBooks() {
        mockMvc
                .perform(get("/book/all").contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", is(Collections.emptyList())));
    }

    @Test
    @SneakyThrows
    @Sql(value = {"/script/whenThereIsOneBook/Before.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/whenThereIsOneBook/After.sql"},
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testWhenThereIsOneBook() {
        mockMvc
                .perform(get("/book/all").contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isOk())

                .andExpect(jsonPath("$.[0].id", is(4)))
                .andExpect(jsonPath("$.[0].title", is("War and Peace")))
                .andExpect(jsonPath("$.[0].author", is("L. Tolstoy")))
                .andExpect(jsonPath("$.[0].description", nullValue()));
    }

    @Test
    @SneakyThrows
    void testGetGroupedBooks() {
        mockMvc
                .perform(get("/book/all/grouped").contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isOk())

                .andExpect(jsonPath("$.[0].author", is("F. Dostoevsky")))
                .andExpect(jsonPath("$.[0].bookCount", is(2)))
                .andExpect(jsonPath("$.[0].bookTitles", is("Crime and Punishment, The Brothers Karamazov")))

                .andExpect(jsonPath("$.[1].author", is("N. Gogol")))
                .andExpect(jsonPath("$.[1].bookCount", is(1)))
                .andExpect(jsonPath("$.[1].bookTitles", is("Dead Souls")))

                .andExpect(jsonPath("$.[2].author", is("L. Tolstoy")))
                .andExpect(jsonPath("$.[2].bookCount", is(2)))
                .andExpect(jsonPath("$.[2].bookTitles", is("Anna Karenina, War and Peace")));
    }

    @Test
    @SneakyThrows
    @Sql(value = {"/script/getGroupedBooksWhenThereAreNoBooks/Before.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/getGroupedBooksWhenThereAreNoBooks/After.sql"},
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testGetGroupedBooksWhenThereAreNoBooks() {
        mockMvc
                .perform(get("/book/all/grouped").contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", is(Collections.emptyList())));
    }

    @Test
    @SneakyThrows
    @Sql(value = {"/script/getGroupedBooksWhenThereIsOneAuthor/Before.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/getGroupedBooksWhenThereIsOneAuthor/After.sql"},
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testGetGroupedBooksWhenThereIsOneAuthor() {
        mockMvc
                .perform(get("/book/all/grouped").contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isOk())

                .andExpect(jsonPath("$.[0].author", is("F. Dostoevsky")))
                .andExpect(jsonPath("$.[0].bookCount", is(2)))
                .andExpect(jsonPath("$.[0].bookTitles", is("Crime and Punishment, The Brothers Karamazov")));
    }


    @Test
    @SneakyThrows
    @Sql(value = {"/script/testGetByCharWithA/Before.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/testGetByCharWithA/After.sql"},
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testGetByCharCountWith_A_Char() {
        mockMvc
                .perform(get("/book/all/by-book-letter/{bookLetter}", "a")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].author", is("L. Tolstoy")))
                .andExpect(jsonPath("$.[0].charCount", is(7)))

                .andExpect(jsonPath("$.[1].author", is("F. Dostoevsky")))
                .andExpect(jsonPath("$.[1].charCount", is(4)))

                .andExpect(jsonPath("$.[2].author", is("N. Gogol")))
                .andExpect(jsonPath("$.[2].charCount", is(1)));
    }

    @Test
    @SneakyThrows
    @Sql(value = {"/script/testGetByCharWithB/Before.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/testGetByCharWithB/After.sql"},
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testGetByCharCountWith_B_Char() {
        mockMvc
                .perform(get("/book/all/by-book-letter/{bookLetter}", "b")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.[0].author", is("F. Dostoevsky")))
                .andExpect(jsonPath("$.[0].charCount", is(1)));
    }

    @Test
    @SneakyThrows
    @Sql(value = {"/script/testGetByCharWhenDoesntMatch/Before.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/testGetByCharWhenDoesntMatch/After.sql"},
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testGetByCharCountWhenDoesntMatch() {
        mockMvc
                .perform(get("/book/all/by-book-letter/{bookLetter}", "f")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$", is(Collections.emptyList())));
    }

    @Test
    @SneakyThrows
    @Sql(value = {"/script/testGetByCharWhenDigitAsString/Before.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/testGetByCharWhenDigitAsString/After.sql"},
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testGetByCharCountWithDigitAsString() {
        mockMvc
                .perform(get("/book/all/by-book-letter/{bookLetter}", "2")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$", is(Collections.emptyList())));
    }


    @Test
    @SneakyThrows
    @Sql(value = {"/script/testAddBook/Before.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/testAddBook/After.sql"},
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testAddBook() {
        BookRequestDto bookRequestDto = BookRequestFaker.getBookRequestDto();
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc
                .perform(
                        post("/book/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(bookRequestDto))
                )
                .andExpect(status().isOk())
                .andDo(result -> {
                    String contentAsString = result.getResponse().getContentAsString();
                    BookIdResponseDto bookIdResponseDto = objectMapper.readValue(contentAsString, BookIdResponseDto.class);
                    String checkQuery = """
                            SELECT EXISTS(SELECT 1 FROM book_entity WHERE id = :id)
                            """;
                    Boolean singleResult = (Boolean) entityManager.createNativeQuery(checkQuery)
                            .setParameter("id", bookIdResponseDto.getBookId())
                            .getSingleResult();
                    Assertions.assertEquals(true, singleResult);
                });
    }
}
