package com.kitaplik.library_service.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibraryDto {
    public String id;
    public List<BookDto> userBookList = new ArrayList<>();

    public LibraryDto(String id) {
        this.id = id;
    }
}
