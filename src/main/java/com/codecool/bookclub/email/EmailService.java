package com.codecool.bookclub.email;

import com.codecool.bookclub.event.model.Event;

public interface EmailService {
    void sendRegistrationEmail();
    void sendNewEventCreatedEmail(Event event);
    void sendJoinEventEmail(Event event);
    void sendSignUpForEventWaitingListEmail(Event event);
    void sendEventResignationEmail(Event event);
    void sendMovingFromWaitingListToParticipantEmail();

}
