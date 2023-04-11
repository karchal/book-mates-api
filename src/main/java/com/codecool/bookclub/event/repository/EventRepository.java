package com.codecool.bookclub.event.repository;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.book.model.BookDetails;
import com.codecool.bookclub.event.dto.EventDto;
import com.codecool.bookclub.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EventRepository extends JpaRepository<Event, Long> {
    Event findEventById(long id);

    List<Event> findFirst4ByOrderByCreationDateAndTimeDesc();

    List<Event> findAllByParticipantsId(long userId);


}
