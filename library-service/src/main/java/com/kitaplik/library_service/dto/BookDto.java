package com.kitaplik.library_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    public BookIdDto bookId;
    public String title;
    public int bookYear;
    public String author;
    public String pressName;

    public BookDto(BookIdDto bookId){
        this.bookId = bookId;
    }

}
