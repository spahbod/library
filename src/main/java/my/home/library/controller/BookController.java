package my.home.library.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.home.library.model.Author;
import my.home.library.model.Book;
import my.home.library.service.BookService;
import my.home.library.utility.Constraint;
import my.home.library.wrapper.BookViewWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

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
        List<BookViewWrapper> bookViews = bookService.getBooksViews();
        model.addAttribute("bookViews", bookViews);
        watch.stop();
        log.info("getAllBooks : {} size took : {} millis", bookViews.size(), watch.getTotalTimeMillis());
        return Constraint.INDEX;
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        StopWatch watch = new StopWatch();
        watch.start();
        Book employee = bookService.getBookById(id);
        model.addAttribute("book", employee);
        watch.stop();
        log.info("showFormForUpdate took : {} millis", watch.getTotalTimeMillis());
        return "update";
    }

    @GetMapping("/viewBook/{id}")
    public String viewBook(@PathVariable(value = "id") long id, Model model) {
        StopWatch watch = new StopWatch();
        watch.start();
        Book employee = bookService.getBookById(id);
        model.addAttribute("book", employee);
        watch.stop();
        log.info("viewBook took : {} millis", watch.getTotalTimeMillis());
        return "view";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBookById(@PathVariable(value = "id") long id) {
        StopWatch watch = new StopWatch();
        watch.start();
        bookService.deleteBookById(id);
        watch.stop();
        log.info("deleteBook took : {} millis", watch.getTotalTimeMillis());
        return "redirect:/";
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
        return Constraint.NEWBOOK;
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

    @PostMapping("/update")
    public String updateBook(@ModelAttribute("book") Book book) {
        StopWatch watch = new StopWatch();
        watch.start();
        bookService.saveBook(book);
        watch.stop();
        log.info("updateBook took : {} millis", watch.getTotalTimeMillis());
        return "redirect:/";
    }


    @PostMapping("/addAuthorToModel")
    public String addAuthorToModel(@ModelAttribute("book") Book book) {
        StopWatch watch = new StopWatch();
        watch.start();
        if (book != null) {
            book.addAuthor(new Author());
        }
        watch.stop();
        log.info("addAuthorToModel took : {} millis", watch.getTotalTimeMillis());
        return Constraint.NEWBOOK;
    }

    @PostMapping("/deleteAuthorFromModel")
    public String deleteAuthorFromModel(@ModelAttribute("book") Book book, @RequestParam("removeItem") int index) {
        StopWatch watch = new StopWatch();
        watch.start();
        if (book != null && book.getAuthors() != null && !book.getAuthors().isEmpty()) {
            log.info("removing book from index {} from {}", index, book.getAuthors().size());
            Author author = book.getAuthors().get(index);
            book.removeAuthor(author);
        } else {
            log.info("something wrong with deleteAuthorFromModel for index {}", index);
        }
        watch.stop();
        log.info("deleteAuthorFromModel took : {} millis", watch.getTotalTimeMillis());
        return Constraint.NEWBOOK;
    }
}
