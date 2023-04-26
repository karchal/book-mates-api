package com.codecool.bookclub.book.service;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.googleapi.GoogleApiBook;
import com.codecool.bookclub.googleapi.ReturnResults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
        if (results.getItems() != null)
            return results.getItems().stream().map(this::convertToBook).collect(Collectors.toList());
        else
            return new ArrayList<>();
    }


    public Book getBookByExternalId(String externalId) {
        return convertToBook(callApiByBookId(externalId));
    }

    private GoogleApiBook callApiByBookId(String externalId) {
        String url = googleApiUrl + API_PARAM_VOLUME + externalId;
        log.debug("Url for API: {}", url);
        WebClient.Builder builder = WebClient.builder();
        return builder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(GoogleApiBook.class)
                .block();
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