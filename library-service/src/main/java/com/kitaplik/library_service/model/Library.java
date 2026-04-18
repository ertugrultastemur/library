package com.kitaplik.library_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Library {
    @Id
    @Column(name = "library_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    public String id;

    @ElementCollection(fetch = FetchType.LAZY)
    public List<String> userBook = new ArrayList<>();
}
