package my.home.library.controller;

import lombok.extern.slf4j.Slf4j;
import my.home.library.model.Book;
import my.home.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String getAllBooks(Model model) {
        List<Book> books = bookService.list();
        log.info("Returning books {}", books.size());
        model.addAttribute("allbooks", books);
        return "index";
    }
}
