package com.codecool.bookclub.email;

public interface EmailService {
    void sendRegistrationEmail();
    void sendNewEventCreatedEmail();
    void sendJoinEventEmail();
    void sendSignUpForEventWaitingListEmail();
    void sendEventResignationEmail();
    void sendMovingFromWaitingListToParticipantEmail();

}
