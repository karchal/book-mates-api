package com.codecool.bookclub.event.service;

import com.codecool.bookclub.event.model.Event;
import com.codecool.bookclub.event.repository.EventRepository;
import com.codecool.bookclub.user.model.Role;
import com.codecool.bookclub.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents(){
        return eventRepository.findAll(Sort.by(Sort.Direction.DESC, "creationDateAndTime"));
    }

    public Event getEventById(long eventId){
        return eventRepository.findEventById(eventId);
    }

    public void addEvent(String title, String description, LocalDateTime eventDate, int maxParticipants, String url){
        eventRepository.save(new Event(title,description,eventDate,maxParticipants,url));
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
}
