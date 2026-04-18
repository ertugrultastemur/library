package com.kitaplik.book_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookIdDto {

    String bookId;
    String isbn;

    public static BookIdDto convert(String bookId, String isbn){
        return new BookIdDto(bookId, isbn);
    }

}
