package my.home.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Author {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surName;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();
}
