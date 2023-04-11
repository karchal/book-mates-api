package com.codecool.bookclub.event.service;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.book.repository.BookRepository;
import com.codecool.bookclub.event.dto.EventDto;
import com.codecool.bookclub.event.model.Event;
import com.codecool.bookclub.event.repository.EventPaginationRepository;
import com.codecool.bookclub.event.repository.EventRepository;
import com.codecool.bookclub.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService{

    private final EventRepository eventRepository;
    private final BookRepository bookRepository;
    private final EventPaginationRepository paginationRepository;

    @Autowired
    public EventService(EventRepository eventRepository, BookRepository bookRepository, EventPaginationRepository paginationRepository) {
        this.eventRepository = eventRepository;
        this.bookRepository = bookRepository;
        this.paginationRepository = paginationRepository;
    }

    public List<EventDto> getAllEvents(){
        return eventRepository.findAll(Sort.by(Sort.Direction.DESC, "creationDateAndTime")).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Page<EventDto> findAllEvents(Pageable paging){
        return paginationRepository.findAll(paging).map(this::convertToDto);
    }

    public EventDto getEventById(long eventId){
        return  convertToDto(eventRepository.findEventById(eventId)) ;
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
            Event updatedEvent = eventRepository.findById(eventId).orElse(new Event());  // ?
            updatedEvent.setTitle(event.getTitle());
            updatedEvent.setDescription(event.getDescription());
            updatedEvent.setEventDate(event.getEventDate());
            updatedEvent.setMaxParticipants(event.getMaxParticipants());
            updatedEvent.setUrl(event.getUrl());
            eventRepository.save(updatedEvent);
        }
    }

    public List<EventDto> findTopFourEvents() {
        return eventRepository.findFirst4ByOrderByCreationDateAndTimeDesc()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public EventDto convertToDto(Event event){
        return EventDto.builder()
                .id(event.getId())
                .eventType(event.getEventType())
                .eventDate(event.getEventDate())
                .title(event.getTitle())
                .pictureUrl(event.getBook().getPictureUrl())
                .creationDateAndTime(event.getCreationDateAndTime())
                .url(event.getUrl())
                .description(event.getDescription())
                .maxParticipants(event.getMaxParticipants())
                .organizerId(event.getOrganizer().getId())
                .participantId(event.getParticipants().stream().map(User::getId).collect(Collectors.toList()))
                .bookId(event.getBook().getId())
                .bookTitle(event.getBook().getTitle())
                .bookAuthor(event.getBook().getAuthor())
                .build();
    }
}
