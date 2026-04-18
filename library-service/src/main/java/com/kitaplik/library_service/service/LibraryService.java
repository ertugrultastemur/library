package com.kitaplik.library_service.service;

import com.kitaplik.library_service.client.BookServiceClient;
import com.kitaplik.library_service.dto.AddBookRequest;
import com.kitaplik.library_service.dto.LibraryDto;
import com.kitaplik.library_service.exception.LibraryNotFoundException;
import com.kitaplik.library_service.model.Library;
import com.kitaplik.library_service.repository.LibraryRepository;
import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {
    private Logger logger = LoggerFactory.getLogger(LibraryService.class);
    private final LibraryRepository libraryRepository;
    private final BookServiceClient bookServiceClient;
    public LibraryService(LibraryRepository libraryRepository,  BookServiceClient bookServiceClient) {
        this.libraryRepository = libraryRepository;
        this.bookServiceClient = bookServiceClient;
    }

    public LibraryDto getAllBooksInLibraryById(String id) {
        logger.info("getAllBooksInLibraryById {}", id);
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new LibraryNotFoundException("Library could not found by id: " + id));

        LibraryDto libraryDto = new LibraryDto(library.getId(),
                library.getUserBook()
                        .stream()
                        .map(book -> bookServiceClient.getById(book).getBody())
                        .collect(Collectors.toList()));
        return libraryDto;
    }

    public LibraryDto createLibrary() {
        logger.info("createLibrary");
        Library newLibrary = libraryRepository.save(new Library());
        return new LibraryDto(newLibrary.getId());
    }

    public void addBookToLibrary(AddBookRequest request) {
        logger.info("addBookToLibrary {}", request);
        //BookId bookIdByIsbn = bookServiceBlockingStub.getBookIdByIsbn(Isbn.newBuilder().setIsbn(request.getIsbn()).build());
        String bookId = bookServiceClient.getByIsbn(request.isbn()).getBody().getBookId();

        Library library = libraryRepository.findById(request.id())
                .orElseThrow(() -> new LibraryNotFoundException("Library could not found by id: " + request.id()));

        library.getUserBook()
                .add(bookId);

        libraryRepository.save(library);
    }


    public List<String> getAllLibraries() {
        logger.info("getAllLibraries");
        return libraryRepository.findAll()
                .stream()
                .map(l -> l.getId())
                .collect(Collectors.toList());
    }
}
