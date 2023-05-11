package com.codecool.bookclub.googleapi;


import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

@Data
public class GoogleApiBook {
    private String id;
    private VolumeInfo volumeInfo;

    public static Predicate<GoogleApiBook> distinctByKey() {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(new BookToCompare(t), Boolean.TRUE) == null;
    }

    @Data
    private static class BookToCompare {
        private String title;
        private String author;

        public BookToCompare(GoogleApiBook googleApiBook) {
            this.title = googleApiBook.getVolumeInfo().getTitle().toLowerCase();
            if (googleApiBook.getVolumeInfo().getAuthors() != null && googleApiBook.getVolumeInfo().getAuthors().size() > 0)
                this.author = googleApiBook.getVolumeInfo().getAuthors().get(0).toLowerCase();
        }

    }
}
