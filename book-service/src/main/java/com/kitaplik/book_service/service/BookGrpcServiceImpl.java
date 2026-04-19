package com.kitaplik.book_service.service;

import com.kitaplik.book_service.dto.BookIdDto;
import com.kitaplik.book_service.exception.BookNotFoundException;
import com.kitaplik.book_service.repository.BookRepository;
import com.kitaplik.bookservice.BookId;
import com.kitaplik.bookservice.BookServiceGrpc;
import com.kitaplik.bookservice.Isbn;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BookGrpcServiceImpl extends BookServiceGrpc.BookServiceImplBase {

    Logger logger = LoggerFactory.getLogger(BookGrpcServiceImpl.class);
    private final BookRepository bookRepository;

    public BookGrpcServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void getBookIdByIsbn(Isbn request, StreamObserver<BookId> responseObserver) {
        logger.info("getBookIdByIsbn called by: " + request.getIsbn());
        BookIdDto bookIdDto = bookRepository.findByIsbn(request.getIsbn())
                .map(book ->
                        new BookIdDto(book.getId(), book.getIsbn()))
                .orElseThrow(() ->
                        new BookNotFoundException("Book not found by isbn: " + request.getIsbn()));
        responseObserver
                .onNext(BookId
                        .newBuilder()
                        .setBookId(bookIdDto.getBookId())
                        .setIsbn(bookIdDto.getIsbn())
                .build());
        responseObserver.onCompleted();
    }
}
