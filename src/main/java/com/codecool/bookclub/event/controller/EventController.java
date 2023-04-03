package com.codecool.bookclub.event.controller;

import com.codecool.bookclub.event.model.Event;
import com.codecool.bookclub.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

//    @GetMapping("/books/{book_id}/events")
//    public List<Event> getEventsForBook(@PathVariable("book_id") long bookId){
//        return eventService.getEventById(id);
//        return new ArrayList<>();
//    }

    @GetMapping("/event/{id}") //todo  wrócić do  @GetMapping("/books/{book_id}/events")
    public ResponseEntity<Event> getEventById(@PathVariable("id") long id) {
        Event event = eventService.getEventById(id);
        //todo wykorzystać optional
        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(event, HttpStatus.OK);
        }
    }


    @GetMapping("/events")
    public List<Event> getALlEvents(){
        List<Event> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK).getBody();
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
