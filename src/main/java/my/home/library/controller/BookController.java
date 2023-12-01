package my.home.library.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.home.library.model.Book;
import my.home.library.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        model.addAttribute("allbooks", books);
        return "index";
    }
}
