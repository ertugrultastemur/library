package com.kitaplik.book_service.controller;

import com.kitaplik.book_service.dto.BookDto;
import com.kitaplik.book_service.dto.BookIdDto;
import com.kitaplik.book_service.model.Book;
import com.kitaplik.book_service.repository.BookRepository;
import com.kitaplik.book_service.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/book")
@Validated
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BookDto>> getAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/getByIsbn/{isbn}")
    public ResponseEntity<BookIdDto> getByIsbn(@PathVariable("isbn") @NotNull @NotEmpty String isbn) {
        return ResponseEntity.ok(bookService.findByIsbn(isbn));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable("id") @NotNull @NotEmpty String id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<BookDto> addBook(@Valid @RequestBody BookDto bookDto) {
        bookService.addBook(bookDto);
        return ResponseEntity.ok().build();
    }


}
