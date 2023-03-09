package com.codecool.bookclub.event.controller;

import com.codecool.bookclub.event.service.EventService;
import com.codecool.bookclub.forum.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.events.Event;

import java.util.ArrayList;
import java.util.List;

@RestController()
public class EventController {


    private EventService eventService;

    @GetMapping("/books/{book_id}/events")
    public List<Event> getEventsForBook(@PathVariable("book_id") long id){return new ArrayList<>();}


    @PostMapping("/books/{book_id}/new-event")
    public boolean createEvent(@PathVariable("book_id") long id, @RequestBody Event event){
        return false;
    }

    @PutMapping("events/update-event/{event_id}")
    public boolean updateEvent (@PathVariable("event_id") long id, @RequestBody Event updatedEvent) {
        return false;
    }

    @DeleteMapping("events/delete-event/{event_id}")
    public boolean deleteEvent (@PathVariable("event_id") long id, @RequestBody Event event){
        return false;
    }


}
