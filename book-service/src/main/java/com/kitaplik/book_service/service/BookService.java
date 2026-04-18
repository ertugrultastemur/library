package com.kitaplik.book_service.service;

import com.kitaplik.book_service.dto.BookDto;
import com.kitaplik.book_service.dto.BookIdDto;
import com.kitaplik.book_service.exception.BookNotFoundException;
import com.kitaplik.book_service.model.Book;
import com.kitaplik.book_service.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDto> findAll() {
        logger.debug("Find all books");
        return bookRepository.findAll().stream().map(BookDto::convert).collect(Collectors.toList());
    }

    public BookIdDto findByIsbn(String isbn) {
        logger.debug("Find book by isbn {}", isbn);
        return bookRepository.findByIsbn(isbn).map(book -> new BookIdDto(book.getId(), book.getIsbn())).orElseThrow(() -> new BookNotFoundException("Book not found by isbn: " + isbn));
    }

    public BookDto findById(String id) {
        logger.debug("Find book by id {}", id);
        return bookRepository.findById(id).map(BookDto::convert).orElseThrow(() -> new BookNotFoundException("Book not found by id: " + id));
    }

    public void addBook(BookDto bookDto) {
        logger.debug("Add book {}", bookDto);
        Book book = new Book();
        book.setIsbn(bookDto.getBookId().getIsbn());
        book.setBookYear(bookDto.getBookYear());
        book.setPressName(bookDto.getPressName());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        bookRepository.save(book);
    }

}
