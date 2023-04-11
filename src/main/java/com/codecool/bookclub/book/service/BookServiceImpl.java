package com.codecool.bookclub.book.service;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.book.repository.BookRepository;
import com.codecool.bookclub.googleapi.ReturnResults;
import com.codecool.bookclub.googleapi.VolumeInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Value("${google.books.api.key}")
    private String apiKey;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findBookById(id);
    }

    /*TODO: add parameter page */
    @Override
    public List<Book> getAllBooks() {
        Pageable pageable = PageRequest.of(0, 20, Sort.by("title"));
        List<Book> books = bookRepository.findAll(pageable).getContent();
        return books;
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> findTopFourBooks() {
        List<Book> books = bookRepository.findFirst4ByOrderByRatingDesc();
        //books = books.subList(0, 4);

        return books;
    }

    @Override
    public List<Book> searchBooks(String query) {
       ReturnResults results = callApi(query);
       VolumeInfo volumeInfo = retreiveVolumeInfo(results);

        return null;
    }

    public ReturnResults callApi(String query) {
        String apiUrl = "https://www.googleapis.com/books/v1/volumes?q="+ query + "&key=" + apiKey + "&maxResults=20";
        WebClient.Builder builder = WebClient.builder();
        ReturnResults results = builder.build()
                .get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(ReturnResults.class)
                .block();
        System.out.println(results);
        return results;
    }

    private VolumeInfo retreiveVolumeInfo(ReturnResults results) {

        return null;
    }


//    TODO: methods to serve searching in GoogleApi - one book and books by search param

}
