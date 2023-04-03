package com.codecool.bookclub.event.service;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.book.repository.BookRepository;
import com.codecool.bookclub.event.model.Event;
import com.codecool.bookclub.event.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventService{

    private final EventRepository eventRepository;
    private final BookRepository bookRepository;

    @Autowired
    public EventService(EventRepository eventRepository, BookRepository bookRepository) {
        this.eventRepository = eventRepository;
        this.bookRepository = bookRepository;
    }

    public List<Event> getAllEvents(){
        return eventRepository.findAll(Sort.by(Sort.Direction.DESC, "creationDateAndTime"));
    }

    public Event getEventById(long eventId){
        return eventRepository.findEventById(eventId);
    }

    public void addEvent(long bookId, Event event){
        Book book = bookRepository.findBookById(bookId);
        event.setBook(book);
        eventRepository.save(event);
    }


    public void deleteEventById(long eventId){
        if (getEventById(eventId) != null) {
            eventRepository.deleteById(eventId);
        }
    }

    public void updateEventById(long eventId,Event event){
        if (getEventById(eventId) != null){
            Event updatedEvent = getEventById(eventId);
            updatedEvent.setTitle(event.getTitle());
            updatedEvent.setDescription(event.getDescription());
            updatedEvent.setEventDate(event.getEventDate());
            updatedEvent.setMaxParticipants(event.getMaxParticipants());
            updatedEvent.setUrl(event.getUrl());
            eventRepository.save(updatedEvent);
        }
    }

    public List<Event> findTopFourEvents() {
        return eventRepository.findFirst4ByOrderByCreationDateAndTimeDesc();
    }
}
