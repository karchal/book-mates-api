package com.codecool.bookclub.book.service;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.googleapi.GoogleApiBook;
import com.codecool.bookclub.googleapi.ReturnResults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GoogleApiBookService {
    public static final String API_PARAM_VOLUMES = "volumes?q=";
    public static final String API_PARAM_KEY = "&key=";
    public static final String API_PARAM_MAX_RESULTS = "&maxResults=";
    static final String API_PARAM_RESULTS_NUMBER = "20";
    public static final int RATING_RATIO = 2;
    public static final String API_PARAM_VOLUME = "volumes/";
    public static final int DEFAULT_PUBLICATION_YEAR = 1970;
    @Value("${google.books.api.key}")
    String apiKey;
    @Value("${google.books.api.url}")
    String googleApiUrl;

    public GoogleApiBookService() {
    }

    public List<Book> searchBooks(String criteria, String query) {
        ReturnResults results = callApi(criteria, query);
        if (results.getItems() != null) {
            List<Book> booksFromSearch = results.getItems().stream().map(this::convertToBook).collect(Collectors.toList());
            return checkIfUnique(booksFromSearch);
        }
        else
            return new ArrayList<>();
    }

    private List<Book> checkIfUnique(List<Book> books) {
        List<Book> uniqueBooks = books.stream()
                .distinct()
                .filter(book -> books.stream()
                    .filter(otherBook -> book != otherBook)
                    .noneMatch(otherBook -> BookUtils.isDistinctByTitleAndAuthor(book, otherBook)))
                .collect(Collectors.toList());
        return uniqueBooks;
    }

//    private boolean isDistinctByTitleAndAuthor(Book book, Book book1) {
//        if (book == null || book1 == null || book.getAuthor() == null || book1.getAuthor() == null ||
//                book.getTitle() == null || book1.getTitle() == null) {
//            return false;
//        }
//        return ((book.getAuthor().equalsIgnoreCase(book1.getAuthor())) && (book.getTitle().equalsIgnoreCase(book1.getTitle())));
//    }


    public Book getBookByExternalId(String externalId) {
        return convertToBook(callApiByBookId(externalId));
    }
    /* TODO serve api errors */
    private GoogleApiBook callApiByBookId(String externalId) {
        String url = googleApiUrl + API_PARAM_VOLUME + externalId;
        log.debug("Url for API: {}", url);
        return getGoogleApiBook(externalId, url);
    }

    private static GoogleApiBook getGoogleApiBook(String externalId, String url) {
        WebClient.Builder builder = WebClient.builder();
        try {
            return builder.build()
                    .get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(GoogleApiBook.class)
                    .block();
        } catch (WebClientResponseException exception) {
            log.error("Error calling Google Books API for book by id: {}", exception.getMessage());
            if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new BookNotFoundException("Book not found for id " + externalId);
            } else {
                throw new ApiException("Error when calling Google Books API: " + exception.getMessage());
            }
        }
    }

    private ReturnResults callApi(String criteria, String query) {
        if ((query == null || query.isBlank()) && (criteria == null || criteria.isBlank())) {
            ReturnResults rr = new ReturnResults();
            rr.setTotalItems(0);
            rr.setItems(new ArrayList<GoogleApiBook>());
            return rr;
        }
        String url = googleApiUrl + API_PARAM_VOLUMES;
        if (query != null && !query.isBlank()) {
            url = url + criteria + ":\"" + query + "\"";
        }
        url = url + API_PARAM_KEY + apiKey + API_PARAM_MAX_RESULTS + API_PARAM_RESULTS_NUMBER;
        log.debug("Url for API: {}", url);
        return getReturnResults(criteria, query, url);
    }

    private static ReturnResults getReturnResults(String criteria, String query, String url) {
        WebClient.Builder builder = WebClient.builder();
        try {
            return builder.build()
                    .get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(ReturnResults.class)
                    .block();
        } catch (WebClientResponseException exception) {
            log.error("Error calling Google Books API for books by criteria and query: {}", exception.getMessage());
            if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new BookNotFoundException("Book not found for criteria: " + criteria + " and query: " + query);
            } else {
                throw new ApiException("Error when calling Google Books API: " + exception.getMessage());
            }
        }
    }

    private int extractPublicationYear(GoogleApiBook googleApiBook) {
        String publishedDate = googleApiBook.getVolumeInfo().getPublishedDate();
        int year = DEFAULT_PUBLICATION_YEAR;
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
                .rating(BigDecimal.valueOf(googleApiBook.getVolumeInfo().getAverageRating() != null ? googleApiBook.getVolumeInfo().getAverageRating() * RATING_RATIO : 0))
                .build();
    }
}