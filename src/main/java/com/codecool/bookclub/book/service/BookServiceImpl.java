package com.codecool.bookclub.book.service;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.book.model.Shelf;
import com.codecool.bookclub.book.repository.BookRepository;
import com.codecool.bookclub.googleapi.GoogleApiBook;
import com.codecool.bookclub.googleapi.ReturnResults;
import com.codecool.bookclub.googleapi.VolumeInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
       return results.getItems().stream().map(this::convertToBook).collect(Collectors.toList());
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
        return results;
    }


    public Book convertToBook(GoogleApiBook googleApiBook) {
        return Book.builder()
                .externalId(googleApiBook.getId())
                .title(googleApiBook.getVolumeInfo().getTitle())
                .author(googleApiBook.getVolumeInfo().getAuthors() != null && googleApiBook.getVolumeInfo().getAuthors().size() > 0 ?
                        googleApiBook.getVolumeInfo().getAuthors().get(0) : null)
                .year(Integer.parseInt(googleApiBook.getVolumeInfo().getPublishedDate().substring(0, 3)))
                .description(googleApiBook.getVolumeInfo().getDescription())
                .pictureUrl(googleApiBook.getVolumeInfo().getImageLinks() != null ? googleApiBook.getVolumeInfo().getImageLinks().getSmallThumbnail() : null)
                .pages(googleApiBook.getVolumeInfo().getPageCount())
                .rating(BigDecimal.valueOf(googleApiBook.getVolumeInfo().getAverageRating() != null ? googleApiBook.getVolumeInfo().getAverageRating() * 2 : 0))
                .build();
    }


//    TODO: methods to serve searching in GoogleApi - one book and books by search param
    public Book saveBookToShelf(Book book, Shelf shelf) {
        Optional<Book> bookInDb = bookRepository.findByExternalId(book.getExternalId());
        return bookInDb.orElseGet(() -> bookRepository.save(book));
    }

}
