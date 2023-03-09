package com.codecool.bookclub.event.service;

import com.codecool.bookclub.event.dto.EventDto;
import com.codecool.bookclub.event.model.Event;

import java.util.List;

public interface EventService {

    List<EventDto> findAllEvents();

    Event saveEvent (Event event);

    EventDto findEventById(long id);
}
