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

    public void updateEventById(long eventId,String title, String description, LocalDateTime eventDate, int maxParticipants, String url){
        if (getEventById(eventId).isPresent()){
            Event updatedEvent = getEventById(eventId).get();
            updatedEvent.setTitle(title);
            updatedEvent.setDescription(description);
            updatedEvent.setEventDate(eventDate);
            updatedEvent.setMaxParticipants(maxParticipants);
            updatedEvent.setUrl(url);
            eventRepository.save(updatedEvent);
        }
    }



//    public EventServiceImplementation(EventRepository eventRepository){this.eventRepository = eventRepository;}
//
//
//    @Override
//    public List<EventDto> findAllEvents() {
//        List<Event> events = eventRepository.findAll();
//        return events.stream().map((event) -> mapToEventDto(event)).collect(Collectors.toList());
//    }
//
//    @Override
//    public Event saveEvent(Event event) {
//        return eventRepository.save(event);
//    }
//
//    @Override
//    public EventDto findEventById(long id) {
//       Event event = eventRepository.findById(id).get();
//       return mapToEventDto(event);
//    }

//    private EventDto mapToEventDto(Event event){
//        EventDto eventDto = EventDto.builder()
//                .id(event.getId())
//                .creationDateAndTime(event.getCreationDateAndTime())
//                .title(event.getTitle())
//                .build();
//        return eventDto;
//    }
}
