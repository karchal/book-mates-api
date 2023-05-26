package com.codecool.bookclub.event.service;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.book.repository.BookRepository;
import com.codecool.bookclub.email.EmailService;
import com.codecool.bookclub.event.dto.EventDetailsDto;
import com.codecool.bookclub.event.dto.EventDto;
import com.codecool.bookclub.event.dto.NewEventDto;
import com.codecool.bookclub.event.model.Event;
import com.codecool.bookclub.event.model.EventDetails;
import com.codecool.bookclub.event.model.ParticipantType;
import com.codecool.bookclub.event.repository.EventDetailsRepository;
import com.codecool.bookclub.event.repository.EventPaginationRepository;
import com.codecool.bookclub.event.repository.EventRepository;
import com.codecool.bookclub.user.model.User;
import com.codecool.bookclub.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService{
    private final EventRepository eventRepository;
    private final BookRepository bookRepository;
    private final EventPaginationRepository paginationRepository;
    private final EventDetailsRepository eventDetailsRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    @Autowired
    public EventService(EventRepository eventRepository, BookRepository bookRepository, EventPaginationRepository paginationRepository, EventDetailsRepository eventDetailsRepository, UserRepository userRepository, EmailService emailService) {
        this.eventRepository = eventRepository;
        this.bookRepository = bookRepository;
        this.paginationRepository = paginationRepository;
        this.eventDetailsRepository = eventDetailsRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
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

    public List<EventDto> getEventsByBookExternalId(String bookId){
        return eventRepository.findEventsByBook_ExternalId(bookId).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public void resignFromEvent(long eventId, long userId){
        EventDetails eventDetails = eventDetailsRepository.findAllByEventIdAndUserId(eventId, userId);
        Event event = eventRepository.findEventById(eventId);
        User user = userRepository.findById(userId).orElse(null);
        if (eventDetails.getParticipantType()==ParticipantType.PARTICIPANT || eventDetails.getParticipantType()==ParticipantType.WAITING_LIST){
            eventDetailsRepository.deleteById(eventDetails.getId());
            emailService.sendEventResignationEmail(event, user);
        }
        EventDetails firstOnWaitingList = eventDetailsRepository.findFirstByEventIdAndParticipantType(eventId, ParticipantType.WAITING_LIST);
        if (firstOnWaitingList!=null){
            firstOnWaitingList.setParticipantType(ParticipantType.PARTICIPANT);
            eventDetailsRepository.save(firstOnWaitingList);
            emailService.sendMovingFromWaitingListToParticipantEmail(event, user);
        }

    }

    public void addEvent(String bookId, NewEventDto newEventDto, long userId){
        Book book = bookRepository.findBookByExternalId(bookId);
        Book newBook = new Book();
        if (book==null){
            newBook.setAuthor(newEventDto.getAuthor());
            newBook.setDescription(newEventDto.getBookDescription());
            newBook.setExternalId(newEventDto.getExternalId());
            newBook.setPages(newEventDto.getPages());
            newBook.setPictureUrl(newEventDto.getPictureUrl());
            newBook.setRating(newEventDto.getRating());
            newBook.setTitle(newEventDto.getBookTitle());
            newBook.setYear(newEventDto.getYear());
            bookRepository.save(newBook);
        }
        Event event = new Event();
        event.setBook(Objects.requireNonNullElse(book, newBook));
        event.setTitle(newEventDto.getTitle());
        event.setDescription(newEventDto.getDescription());
        event.setEventDate(newEventDto.getEventDate());
        event.setEventType(newEventDto.getEventType());
        event.setMaxParticipants(newEventDto.getMaxParticipants());
        event.setUrl(newEventDto.getUrl());

        User eventAuthor = userRepository.findById(userId).orElse(null);

        eventRepository.save(event);
        eventDetailsRepository.save(EventDetails.builder()
                .participantType(ParticipantType.ORGANIZER)
                .event(event)
                .user(eventAuthor)
                .build());
        emailService.sendNewEventCreatedEmail(event, eventAuthor);

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

    public ResponseEntity<String> joinEvent(long eventId, long userId) {
        Event event = eventRepository.findEventById(eventId);
        User currentUser = userRepository.findById(userId).orElse(null);
        int currentParticipantsNumber = eventDetailsRepository.findAllByEventId(eventId).size();
        int maxParticipantNumber = event.getMaxParticipants();
        if (isUserAlreadyEventParticipant(event, userId)){
            return ResponseEntity.status(202).body("Już uczestniczysz w tym wydarzeniu.");
        } else if (isEventAlreadyExpired(event)){
            return ResponseEntity.status(202).body("To wydarzenie już się odbyło.");
        } else {
            if (currentParticipantsNumber>=maxParticipantNumber){
                eventDetailsRepository.save(EventDetails.builder()
                        .participantType(ParticipantType.WAITING_LIST)
                        .user(currentUser)
                        .event(event)
                        .build());
                emailService.sendSignUpForEventWaitingListEmail(event, currentUser);
            } else {
                eventDetailsRepository.save(EventDetails.builder()
                        .participantType(ParticipantType.PARTICIPANT)
                        .user(currentUser)
                        .event(event)
                        .build());
                emailService.sendJoinEventEmail(event, currentUser);
            }

            return ResponseEntity.status(200).body("Zostałeś prawidłowo zarejestrowany na wydarzenie.");
        }
    }

    public boolean isUserAlreadyEventParticipant(Event event, long userId){
        List<EventDetails> userEvents = eventDetailsRepository.findAllByUserId(userId);
        boolean isUserAlreadySubscribed = false;
        for (EventDetails userEvent : userEvents) {
            if (event == userEvent.getEvent()) {
                isUserAlreadySubscribed = true;
                break;
            }
        }
        return isUserAlreadySubscribed;
    }
    public boolean isEventAlreadyExpired(Event event){
        ZonedDateTime eventTime = event.getEventDate();
        ZonedDateTime currentTime = ZonedDateTime.now();
        return currentTime.isAfter(eventTime);
    }

    public List<EventDetailsDto> getUserEventDetailDtos(long userId) {
        return eventDetailsRepository.findAllByUserId(userId)
                .stream()
                .map(this::convertDetailsToDto)
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
                .bookId(event.getBook().getId())
                .bookTitle(event.getBook().getTitle())
                .bookAuthor(event.getBook().getAuthor())
                .participants(eventDetailsRepository
                        .findAllByEventId(event.getId())
                        .stream()
                        .filter(participant -> participant.getParticipantType().equals(ParticipantType.PARTICIPANT) || participant.getParticipantType().equals(ParticipantType.ORGANIZER))
                        .toList()
                        .size())
                .waitingList(eventDetailsRepository
                    .findAllByEventId(event.getId())
                    .stream()
                    .filter(participant -> participant.getParticipantType().equals(ParticipantType.WAITING_LIST))
                    .toList()
                    .size())
                .organizatorName(userRepository.findById(eventDetailsRepository.findFirstByEventId(event.getId()).getUser().getId())
                        .map(User::getNickname).orElse(null))
                .build();
    }

    public EventDetailsDto convertDetailsToDto(EventDetails eventDetails){
        return EventDetailsDto.builder()
                .id(eventDetails.getEvent().getId())
                .userId(eventDetails.getUser().getId())
                .eventDate(eventDetails.getEvent().getEventDate())
                .eventId(eventDetails.getEvent().getId())
                .title(eventDetails.getEvent().getTitle())
                .pictureUrl(eventDetails.getEvent().getBook().getPictureUrl())
                .bookId(eventDetails.getEvent().getBook().getId())
                .participantType(eventDetails.getParticipantType())
                .build();
    }

}
