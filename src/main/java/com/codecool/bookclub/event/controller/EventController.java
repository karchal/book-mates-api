package com.codecool.bookclub.event.controller;

import com.codecool.bookclub.event.model.Event;
import com.codecool.bookclub.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
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

    @GetMapping("/events")
    public List<Event> getALlEvents(){
        return eventService.getAllEvents();
    }

    @GetMapping("events/{event_id}")
    public Optional<Event> getEventById(@PathVariable("event_id") long eventId){
        return eventService.getEventById(eventId);
    }

    @PostMapping("/events")
    public void addEvent(@RequestBody Event event){
        eventService.addEvent(event.getTitle(), event.getDescription(), event.getEventDate(), event.getMaxParticipants(), event.getUrl());
    }


//    @PostMapping("/books/{book_id}/new-event")
//    public boolean createEvent(@PathVariable("book_id") long bookId, @RequestBody Event event){
//        return false;
//    }

    @PutMapping("events/{event_id}")
    public void updateEventById (@PathVariable("event_id") long eventId, @RequestBody Event event) {
        eventService.updateEventById(eventId,event);
    }

    @DeleteMapping("events/{event_id}")
    public boolean deleteEventById (@PathVariable("event_id") long eventId){
        return eventService.deleteEventById(eventId);
    }


}
