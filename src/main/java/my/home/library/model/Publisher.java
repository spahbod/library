package my.home.library.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Publisher {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="publisher_id")
    private List<Book> books = new ArrayList<>();
}
