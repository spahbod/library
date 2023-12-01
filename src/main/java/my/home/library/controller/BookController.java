package my.home.library.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.home.library.model.Author;
import my.home.library.model.Book;
import my.home.library.service.BookService;
import my.home.library.wrapper.BookWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
public class BookController {

    private final BookService bookService;

    @GetMapping("/")
    public String getAllBooks(Model model) {
        List<Book> books = bookService.list();


        log.info("Returning books {}", books.size());
        model.addAttribute("allbooks", getBookWrappers(books));
        return "index";
    }

    private List<BookWrapper> getBookWrappers(List<Book> books) {
        List<BookWrapper> bookWrappers = new ArrayList<>();

        for (Book book : books) {
            BookWrapper wrapper = new BookWrapper();
            wrapper.setId(book.getId());
            wrapper.setName(book.getName());

            StringBuilder builder = new StringBuilder();

            for (Author author : book.getAuthors()) {
                builder.append(author.getName());
                builder.append(" ");
                builder.append(author.getSurName());
                builder.append(",");
            }

            String authors = builder.toString();
            authors = authors.substring(0, authors.lastIndexOf(","));
            wrapper.setAuthors(authors);
            bookWrappers.add(wrapper);
        }
        return bookWrappers;
    }
}
