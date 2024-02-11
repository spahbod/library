package my.home.library.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Author {
    @SequenceGenerator(name = "authorSeqGen", sequenceName = "author_id_seq", initialValue = 100, allocationSize = 1)
    @GeneratedValue(generator = "authorSeqGen")
    @Id
    private Long id;
    private String name;
    private String surName;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();
}
