package com.codecool.bookclub.email;

import com.codecool.bookclub.event.model.Event;

public interface EmailService {
    void sendRegistrationEmail();
    void sendNewEventCreatedEmail();
    void sendJoinEventEmail(Event event);
    void sendSignUpForEventWaitingListEmail();
    void sendEventResignationEmail(Event event);
    void sendMovingFromWaitingListToParticipantEmail();

}
