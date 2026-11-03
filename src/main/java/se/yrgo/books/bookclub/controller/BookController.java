package se.yrgo.books.bookclub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.yrgo.books.bookclub.domain.Book;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class BookController {

    @GetMapping("/home")
    public String homePage(Model model) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);

        model.addAttribute("date", formattedDate);
        return "home";
    }


    @GetMapping("/books")
    public String bookList(Model model) {
        List<Book> books = List.of(
                new Book("Silmarillion", "J.R.R. Tolkien", "Fantasy"),
                new Book("Hyperion", "Dan Simmons", "Science Fiction"),
                new Book("House of Earth and Blood", "Sarah J. Maas", "Romance"),
                new Book("The Island of Adventure", "Enid Blyton", "Children")
        );

        model.addAttribute("books", books);
        return "booklist";
    }

    @GetMapping("/genre")
    public String findByGenre(
            @RequestParam(required = false, name = "type")
            String type, Model model) {
        List<Book> books = List.of(
                new Book("Silmarillion", "J.R.R. Tolkien", "Fantasy"),
                new Book("Hyperion", "Dan Simmons", "Science Fiction"),
                new Book("House of Earth and Blood", "Sarah J. Maas", "Romance"),
                new Book("The Island of Adventure", "Enid Blyton", "Children")
        );
        List<Book> filteredBooks = new ArrayList<>();

        if (type == null || type.isBlank()) {
            filteredBooks.addAll(books);
        } else {
            for (Book book : books) {
                if (book.getGenre().equalsIgnoreCase(type)) {
                    filteredBooks.add(book);
                }
            }
        }

        model.addAttribute("books", filteredBooks);

        return "genre";
    }
}
