package my.home.library.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.home.library.service.BookService;
import my.home.library.wrapper.BookViewWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
public class BookController {

    private final BookService bookService;

    @GetMapping("/")
    public String getAllBooks(Model model) {
        StopWatch watch = new StopWatch();
        watch.start();
        List<BookViewWrapper> bookViews = bookService.getAllBooks();
        model.addAttribute("bookViews", bookViews);
        watch.stop();
        log.info("getAllBooks took : {} millis", watch.getTotalTimeMillis());
        return "index";
    }

}
