package my.home.library.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.home.library.model.Book;
import my.home.library.repository.BookRepository;
import my.home.library.utility.Constraint;
import my.home.library.wrapper.BookViewWrapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookViewWrapper> getBooksViews() {
        return getBookViewWrappers(getAllBooks());
    }

    @Cacheable("books")
    public List<Book> getAllBooks() { return bookRepository.findAll(); }

    @CachePut(value = "books", key = "#book.id")
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Cacheable(value = "books", key = "#id")
    public Book getBookById(long id) {
        return bookRepository.getReferenceById(id);
    }

    @CacheEvict(value = "books", key = "#id")
    public void deleteBookById(long id) {
         bookRepository.deleteById(id);
    }

    private List<BookViewWrapper> getBookViewWrappers(List<Book> books) {
        List<BookViewWrapper> bookWrappers = new ArrayList<>();

        for (Book book : books) {
            BookViewWrapper wrapper = new BookViewWrapper();
            wrapper.setId(book.getId());
            wrapper.setName(book.getName());
            wrapper.setPublisher(book.getPublisher().getName());

            if(!book.getAuthors().isEmpty()) {
                String authors = getAuthors(book);
                wrapper.setAuthors(authors);
            }
            bookWrappers.add(wrapper);
        }
        return bookWrappers;
    }

    private String getAuthors(Book book) {
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < Constraint.MAX_ITEMS && i < book.getAuthors().size(); i++){
            builder.append(book.getAuthors().get(i).getName());
            builder.append(Constraint.SPACE);
            builder.append(book.getAuthors().get(i).getSurName());
            builder.append(Constraint.COMMA);
        }

        String authors = builder.toString();
        authors = authors.substring(0, authors.lastIndexOf(Constraint.COMMA));

        if(book.getAuthors().size() > Constraint.MAX_ITEMS){
            authors = authors + " (...)";
        }
        return authors;
    }
}
