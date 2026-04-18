package com.kitaplik.book_service.repository;

import com.kitaplik.book_service.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    @Query("select b from Book b where b.isbn = :isbn")
    Optional<Book>  findByIsbn(String isbn);
}
