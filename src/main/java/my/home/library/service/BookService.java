package my.home.library.service;

import lombok.AllArgsConstructor;
import my.home.library.model.Book;
import my.home.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> list() {
        return bookRepository.findAll();
    }
}
