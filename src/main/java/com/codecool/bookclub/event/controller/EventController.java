package com.codecool.bookclub.event.controller;

import com.codecool.bookclub.event.dto.EventDto;
import com.codecool.bookclub.event.dto.NewEventDto;
import com.codecool.bookclub.event.model.Event;
import com.codecool.bookclub.event.model.EventType;
import com.codecool.bookclub.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

    private final EventService eventService;
    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/event-type/{enum-index}")
    public ResponseEntity<EventType> getEnum(@PathVariable("enum-index")int enumIndex) {
        EventType enumValue = EventType.values()[enumIndex];
        return ResponseEntity.ok(enumValue);
    }

    @GetMapping("/events/{event_id}")
    public ResponseEntity<EventDto> getEventById( @PathVariable("event_id") long eventId) {
        EventDto event = eventService.getEventById(eventId);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @GetMapping("/events/all")
    public ResponseEntity<List<EventDto>> getEvents(){
        List<EventDto> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }


/*TODO wydzielić do stałej liczby */
    @GetMapping("/events")
    public Page<EventDto> getALlEvents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "12") int size){
        Pageable paging = PageRequest.of(page,size, Sort.by("creationDateAndTime").descending());
        return eventService.findAllEvents(paging);
    }
    @GetMapping("/events/top_4")
    public List<EventDto> getFourEvents(){
        List<EventDto> events = eventService.findTopFourEvents();
        return new ResponseEntity<>(events, HttpStatus.OK).getBody();
    }

    @PostMapping("/books/{book_id}/event")
    public void createEvent(@PathVariable("book_id") String bookId, @RequestBody NewEventDto event, @AuthenticationPrincipal Long userId){
        eventService.addEvent(bookId, event, userId);
    }

    @PutMapping("events/{event_id}")
    public void updateEventById (@PathVariable("event_id") long eventId, @RequestBody Event event) {
        eventService.updateEventById(eventId, event);
    }

    @DeleteMapping("events/{event_id}")
    public void deleteEventById (@PathVariable("event_id") long eventId){
         eventService.deleteEventById(eventId);
    }

    @PostMapping("events/{event_id}/join")
    public ResponseEntity<ResponseEntity<String>> joinEvent(@PathVariable("event_id") long eventId, @AuthenticationPrincipal Long userId){
        ResponseEntity<String> joinEvent =  eventService.joinEvent(eventId,userId);
        return new ResponseEntity<>(joinEvent, HttpStatus.OK);
    }
    @GetMapping("books/{book_id}/events")
    public List<EventDto> getEventsByBookExternalId(@PathVariable("book_id") String bookId){
        List<EventDto> events = eventService.getEventsByBookExternalId(bookId);
        return new ResponseEntity<>(events, HttpStatus.OK).getBody();
    }

    @PutMapping("events/{event_id}/resign")
    public void resignFromEvent(@PathVariable("event_id") long eventId, @AuthenticationPrincipal Long userId){
        eventService.resignFromEvent(eventId, userId);
    }

}
