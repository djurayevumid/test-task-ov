package com.example.testtaskov.faker;

import com.example.testtaskov.model.dto.BookRequestDto;
import org.jeasy.random.EasyRandom;

public class BookRequestFaker {

    public static BookRequestDto getBookRequestDto() {
        EasyRandom easyRandom = new EasyRandom();
        return easyRandom.nextObject(BookRequestDto.class);
    }

    public static BookRequestDto getBookRequestDtoWithNullAuthor() {
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor(null);
        bookRequestDto.setTitle("eqweqwe");
        bookRequestDto.setDescription("eqeqwe");
        return bookRequestDto;
    }

    public static BookRequestDto getBookRequestDtoWithNullTitle() {
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor("eqwe");
        bookRequestDto.setTitle(null);
        bookRequestDto.setDescription("eqeqwe");
        return bookRequestDto;
    }

    public static BookRequestDto getBookRequestDtoWithDescription() {
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setAuthor("wqeqwe");
        bookRequestDto.setTitle("eqweqwe");
        bookRequestDto.setDescription(null);
        return bookRequestDto;
    }
}
