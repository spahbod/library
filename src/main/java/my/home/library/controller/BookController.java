package my.home.library.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.home.library.model.Author;
import my.home.library.model.Book;
import my.home.library.service.BookService;
import my.home.library.wrapper.BookViewWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/addnew")
    public String addNewBook(Model model) {
        StopWatch watch = new StopWatch();
        watch.start();
        Book book = new Book();
        book.addAuthor(new Author());
        model.addAttribute("book", book);
        watch.stop();
        log.info("addNewBook took : {} millis", watch.getTotalTimeMillis());
        return "newbook";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book book) {
        StopWatch watch = new StopWatch();
        watch.start();
        bookService.saveBook(book);
        watch.stop();
        log.info("saveBook took : {} millis", watch.getTotalTimeMillis());
        return "redirect:/";
    }

    @PostMapping("/addAuthorToModel")
    public String addAuthorToModel(@ModelAttribute("book") Book book) {
        StopWatch watch = new StopWatch();
        watch.start();
        book.addAuthor(new Author());
        watch.stop();
        log.info("addAuthorToModel took : {} millis", watch.getTotalTimeMillis());
        return "newbook";
    }

    @PostMapping("/deleteAuthorFromModel")
    public String deleteAuthorFromModel(@ModelAttribute("book") Book book, @RequestParam("removeItem") int index) {
        StopWatch watch = new StopWatch();
        watch.start();
        log.info("removing book from index {}", index);
        Author author = book.getAuthors().get(index);
        book.removeAuthor(author);
        watch.stop();
        log.info("deleteAuthorFromModel took : {} millis", watch.getTotalTimeMillis());
        return "newbook";
    }

}
