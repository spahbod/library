package my.home.library.service;

import lombok.AllArgsConstructor;
import my.home.library.model.Author;
import my.home.library.model.Book;
import my.home.library.repository.BookRepository;
import my.home.library.utility.Constraint;
import my.home.library.wrapper.BookViewWrapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookViewWrapper> list() {
        return getBookViewWrappers(bookRepository.findAll());
    }

    private List<BookViewWrapper> getBookViewWrappers(List<Book> books) {
        List<BookViewWrapper> bookWrappers = new ArrayList<>();

        for (Book book : books) {
            BookViewWrapper wrapper = new BookViewWrapper();
            wrapper.setId(book.getId());
            wrapper.setName(book.getName());

            StringBuilder builder = new StringBuilder();

            for (Author author : book.getAuthors()) {
                builder.append(author.getName());
                builder.append(Constraint.SPACE);
                builder.append(author.getSurName());
                builder.append(Constraint.COMMA);
            }

            String authors = builder.toString();
            authors = authors.substring(0, authors.lastIndexOf(Constraint.COMMA));
            wrapper.setAuthors(authors);
            bookWrappers.add(wrapper);
        }
        return bookWrappers;
    }
}
