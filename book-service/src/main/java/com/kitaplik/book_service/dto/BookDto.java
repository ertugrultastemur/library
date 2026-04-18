package com.kitaplik.book_service.dto;

import com.kitaplik.book_service.model.Book;
import com.kitaplik.book_service.service.BookService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookDto {
    public BookIdDto bookId;
    public String title;
    public int bookYear;
    public String author;
    public String pressName;

    public static BookDto convert(Book book){
        return new BookDto(
                BookIdDto.convert(book.getId(), book.getIsbn()),
                book.title,
                book.bookYear,
                book.author,
                book.pressName
        );
    }
}
