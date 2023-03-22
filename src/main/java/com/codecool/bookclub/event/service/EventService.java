package com.codecool.bookclub.event.service;

import com.codecool.bookclub.event.model.Event;
import com.codecool.bookclub.event.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public Optional<Event> getEventById(long eventId){
        return eventRepository.findById(eventId);
    }

    public void addEvent(String title, String description, LocalDateTime eventDate, int maxParticipants, String url){
        eventRepository.save(new Event(title,description,eventDate,maxParticipants,url));
    }

    public boolean deleteEventById(long eventId){
        return getEventById(eventId).map(event -> {
            eventRepository.deleteById(eventId);
            return true;
        }).orElse(false);
    }

    public void updateEventById(long eventId,Event event){
        if (getEventById(eventId).isPresent()){
            Event updatedEvent = getEventById(eventId).get();
            updatedEvent.setTitle(event.getTitle());
            updatedEvent.setDescription(event.getDescription());
            updatedEvent.setEventDate(event.getEventDate());
            updatedEvent.setMaxParticipants(event.getMaxParticipants());
            updatedEvent.setUrl(event.getUrl());
            eventRepository.save(updatedEvent);
        }
    }
}
