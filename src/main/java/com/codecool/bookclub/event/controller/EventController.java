package com.codecool.bookclub.event.controller;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.book.service.BookService;
import com.codecool.bookclub.event.model.Event;
import com.codecool.bookclub.event.model.EventType;
import com.codecool.bookclub.event.service.EventService;
import org.hibernate.type.EnumType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

    private final EventService eventService;
    private final BookService bookService;


    @Autowired
    public EventController(EventService eventService, BookService bookService) {
        this.eventService = eventService;
        this.bookService = bookService;
    }

//    @GetMapping("/books/{book_id}/events")
//    public List<Event> getEventsForBook(@PathVariable("book_id") long bookId){
//        return eventService.getEventById(id);
//        return new ArrayList<>();
//    }

    @GetMapping("/books/{book_id}/events/{event_id}")
    public void getEventById(@PathVariable("book_id") long bookId, @PathVariable("event_id") long eventId) {
//        Optional<Event> event = eventService.getEventById(eventId);
//        if (event.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<>(event.get(), HttpStatus.OK);
//        }
    }

    @GetMapping("/event-type/{enum-index}")
    public ResponseEntity<EventType> getEnum(@PathVariable("enum-index")int enumIndex) {
        EventType enumValue = EventType.values()[enumIndex];
        return ResponseEntity.ok(enumValue);
    }


    @GetMapping("/events/{event_id}")
    public ResponseEntity<Event> getEventById( @PathVariable("event_id") long eventId) {
        Event event = eventService.getEventById(eventId);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }



    @GetMapping("/events")
    public Page<Event> getALlEvents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "12") int size){
        Pageable paging = PageRequest.of(page,size, Sort.by("creationDateAndTime").descending());
//        List<Event> events = eventService.getAllEvents();
//        return new ResponseEntity<>(events, HttpStatus.OK).getBody();
        return eventService.findAllEvents(paging);
    }
    @GetMapping("/events/top_4")
    public List<Event> getFourEvents(){
        List<Event> events = eventService.findTopFourEvents();
        return new ResponseEntity<>(events, HttpStatus.OK).getBody();
    }

    @PostMapping("/books/{book_id}/event")
    public void createEvent(@PathVariable("book_id") long bookId, @RequestBody Event event){
        eventService.addEvent(bookId,event);
    }

    @PutMapping("events/{event_id}")
    public void updateEventById (@PathVariable("event_id") long eventId, @RequestBody Event event) {
        eventService.updateEventById(eventId, event);
    }

    @DeleteMapping("events/{event_id}")
    public void deleteEventById (@PathVariable("event_id") long eventId){
         eventService.deleteEventById(eventId);
    }


}
