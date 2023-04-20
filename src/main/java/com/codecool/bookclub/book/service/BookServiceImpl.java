package com.codecool.bookclub.book.service;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.book.model.Shelf;
import com.codecool.bookclub.book.repository.BookRepository;
import com.codecool.bookclub.googleapi.GoogleApiBook;
import com.codecool.bookclub.googleapi.ReturnResults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Value("${google.books.api.key}")
    private String apiKey;

    @Value("${google.books.api.url}")
    private String googleApiUrl;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findBookById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public List<Book> findTopFourBooks() {
        List<Book> books = bookRepository.findFirst4ByOrderByRatingDesc();
        return books;
    }

    @Override
    public List<Book> searchBooks(String criteria, String query) {
       ReturnResults results = callApi(criteria, query);
       if (results.getItems() != null)
           return results.getItems().stream().map(this::convertToBook).collect(Collectors.toList());
       else
           return new ArrayList<>();
    }

    public Book getBookByExternalId(String externalId) {
        return convertToBook(callApiByBookId(externalId));
    }

    private GoogleApiBook callApiByBookId(String externalId) {
        String url = googleApiUrl + "volumes/" + externalId;
        log.debug("Url for API: {}", url);
        WebClient.Builder builder = WebClient.builder();
        return builder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(GoogleApiBook.class)
                .block();
    }

    public Book saveBookToShelf(Book book, Shelf shelf) {
        Optional<Book> bookInDb = bookRepository.findByExternalId(book.getExternalId());
        return bookInDb.orElseGet(() -> bookRepository.save(book));
    }

//    public ReturnResults callApi(String query) {
//        String apiUrl = googleApiUrl + "volumes?q=" + query + "&key=" + apiKey + "&maxResults=20";
//        WebClient.Builder builder = WebClient.builder();
//        return builder.build()
//                .get()
//                .uri(apiUrl)
//                .retrieve()
//                .bodyToMono(ReturnResults.class)
//                .block();
//    }

    private ReturnResults callApi(String criteria, String query) {
        if (query == null || query.isBlank() && criteria == null || criteria.isBlank()) {
            ReturnResults rr = new ReturnResults();
            rr.setTotalItems(0);
            rr.setItems(new ArrayList<>());
            return rr;
        }
        String url = googleApiUrl + "volumes?q=";
        if (query != null && !query.isBlank()) {
            url = url + criteria + ":\"" + query + "\"";
        }
        url = url + "&key=" + apiKey + "&maxResults=20";
        log.debug("Url for API: {}", url);
        WebClient.Builder builder = WebClient.builder();
        return builder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(ReturnResults.class)
                .block();
    }

    private int extractPublicationYear(GoogleApiBook googleApiBook) {
        String publishedDate = googleApiBook.getVolumeInfo().getPublishedDate();
        int year = 1970;
        if (publishedDate != null) {
            Pattern pattern = Pattern.compile("\\d{4}");
            Matcher matcher = pattern.matcher(publishedDate);
            if (matcher.find()) {
                return Integer.parseInt(matcher.group());
            }
        }
        return year;
    }


    private Book convertToBook(GoogleApiBook googleApiBook) {
        return Book.builder()
                .externalId(googleApiBook.getId())
                .title(googleApiBook.getVolumeInfo().getTitle())
                .author(googleApiBook.getVolumeInfo().getAuthors() != null && googleApiBook.getVolumeInfo().getAuthors().size() > 0 ?
                        googleApiBook.getVolumeInfo().getAuthors().get(0) : null)
                .year(extractPublicationYear(googleApiBook))
                .description(googleApiBook.getVolumeInfo().getDescription() != null ? googleApiBook.getVolumeInfo().getDescription() : null)
                .pictureUrl(googleApiBook.getVolumeInfo().getImageLinks() != null ? googleApiBook.getVolumeInfo().getImageLinks().getThumbnail() : null)
                .pages(googleApiBook.getVolumeInfo().getPageCount())
                .rating(BigDecimal.valueOf(googleApiBook.getVolumeInfo().getAverageRating() != null ? googleApiBook.getVolumeInfo().getAverageRating() * 2 : 0))
                .build();
    }


}
