package com.codecool.bookclub.book.service;

import com.codecool.bookclub.book.dto.BookDetailsDto;
import com.codecool.bookclub.book.model.BookDetails;
import com.codecool.bookclub.book.repository.BookDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookDetailsService {

    private final BookDetailsRepository bookDetailsRepository;

    public List<BookDetailsDto> getBookDetailsDtos(long userId) {
        return bookDetailsRepository.findAllByUserId(userId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public BookDetailsDto convertToDto(BookDetails bookDetails){
        return BookDetailsDto.builder()
                .bookDetailsId(bookDetails.getId())
                .externalId(bookDetails.getBook().getExternalId())
                .title(bookDetails.getBook().getTitle())
                .author(bookDetails.getBook().getAuthor())
                .pictureUrl(bookDetails.getBook().getPictureUrl())
                .shelf(bookDetails.getShelf())
                .userRating(bookDetails.getUserRating())
                .build();
    }



}
