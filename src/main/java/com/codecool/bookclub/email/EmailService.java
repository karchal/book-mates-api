package com.codecool.bookclub.email;

import com.codecool.bookclub.event.model.Event;
import com.codecool.bookclub.user.model.User;

public interface EmailService {
    void sendRegistrationEmail(String userEmail, String token);
    void sendNewEventCreatedEmail(Event event, User user);
    void sendJoinEventEmail(Event event, User user);
    void sendSignUpForEventWaitingListEmail(Event event, User user);
    void sendEventResignationEmail(Event event, User user);
    void sendMovingFromWaitingListToParticipantEmail(Event event, User user);
    void sendPasswordRecoverEmail(String userEmail, String recoveryLink);

}
