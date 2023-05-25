package com.codecool.bookclub.event.repository;

import com.codecool.bookclub.event.model.EventDetails;
import com.codecool.bookclub.event.model.ParticipantType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventDetailsRepository extends JpaRepository<EventDetails, Long> {
    List<EventDetails> findAllByUserId(long userId);
    EventDetails findFirstByEventId(long eventId);
    List<EventDetails> findAllByEventId(long eventId);
    EventDetails findAllByEventIdAndUserId(long eventId, long userId);
    EventDetails findFirstByEventIdAndParticipantType(long eventId, ParticipantType participantType);

}
