package com.kitaplik.library_service.client;

import com.kitaplik.library_service.dto.BookDto;
import com.kitaplik.library_service.dto.BookIdDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "book-service", path = "/v1/book")
public interface BookServiceClient {
    @GetMapping("/getAll")
    ResponseEntity<List<BookDto>> getAll();

    @GetMapping("/getByIsbn/{isbn}")
    @CircuitBreaker(name = "getByIsbnCircuitBreaker", fallbackMethod = "getByIsbnFallback")
    ResponseEntity<BookIdDto> getByIsbn(@PathVariable("isbn") String isbn);

    default ResponseEntity<BookIdDto> getByIsbnFallback(String isbn, Exception exception) {
        return ResponseEntity.ok(new BookIdDto("defaultid", "defaultisbn"));
    }

    @GetMapping("/getById/{id}")
    @CircuitBreaker(name = "getByIdCircuitBreaker", fallbackMethod = "getByIdFallback")
    ResponseEntity<BookDto> getById(@PathVariable("id") String id);

    default ResponseEntity<BookDto> getByIdFallback(String id, Exception exception) {
        return ResponseEntity.ok(new BookDto(new BookIdDto("defaultid","isbn")));
    }

    @PostMapping("/add")
    ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto);
}
