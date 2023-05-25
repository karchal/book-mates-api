package com.codecool.bookclub.email;

import com.codecool.bookclub.event.model.Event;
import com.codecool.bookclub.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendRegistrationEmail(String userEmail) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("contact.bookmates@gmail.com");
        email.setTo(userEmail);
        email.setSubject("Bookmates - Pomyślna rejestracja!");
        email.setText("Drogi użytkowniku, \n\n" +
                "Twoja rejestracja zakończyła się sukcesem. \n" +
                "Jesteśmy podekscytowani, że dołączyłeś do naszej dynamicznej społeczności miłośników książek. \n\n " +
                "Pozdrawiamy,\n " +
                "Zespół Bookmates");
        mailSender.send(email);
    }

    @Override
    public void sendNewEventCreatedEmail(Event event, User user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("contact.bookmates@gmail.com");
        email.setTo(user.getEmail());
        email.setSubject("Bookmates - Potwierdzenie utworzenia wydarzenia!");
        email.setText("Drogi użytkowniku, \n\n" +
                "Twoje wydarzenie zostało pomyślnie utworzone i jest teraz widoczne dla innych użytkowników. \n\n" +
                "Nazwa wydarzenia: " + event.getTitle() + "\n" +
                "Data wydarzenia: " + event.getEventDate() + "\n" +
                "Opis wydarzenia: " + event.getDescription() + "\n\n" +
                "Jeśli masz jakiekolwiek pytania, " +
                "potrzebujesz wprowadzić zmiany lub skasować wydarzenie, prosimy o kontakt. \n" +
                "Nasz zespół jest gotowy do udzielenia Ci wsparcia i odpowiedzi na wszystkie pytania. \n" +
                "Aby skontaktować się z nami, możesz odpowiedzieć na tę wiadomość email lub " +
                "skorzystać z formularza kontaktowego dostępnego na naszej stronie internetowej. \n\n" +
                "Dziękujemy, że jesteś częścią społeczności miłośników książek. \n\n" +
                "Pozdrawiamy,\n " +
                "Zespół Bookmates");
        mailSender.send(email);
    }

    @Override
    public void sendJoinEventEmail(Event event, User user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("contact.bookmates@gmail.com");
        email.setTo(user.getEmail());
        email.setSubject("Bookmates - Potwierdzenie rejestracji na wydarzenie!");
        email.setText("Drogi użytkowniku, \n\n" +
                "Dziękujemy za zapisanie się na nasze nadchodzące wydarzenie. \n\n" +
                "Informacji dotyczących wydarzenia: \n" +
                "Nazwa wydarzenia: " + event.getTitle() + "\n" +
                "Data wydarzenia: " + event.getEventDate() + "\n" +
                "Opis wydarzenia: " + event.getDescription() + "\n\n" +
                "W przypadku rezygnacji z udziału w wydarzeniu, prosimy o naciśnięcie na poniższy link: \n\n" +
                "http://localhost:3000/events/" + event.getId() + "/resign \n\n" +
                "Dziękujemy, że jesteś częścią społeczności miłośników książek. \n\n" +
                "Pozdrawiamy,\n " +
                "Zespół Bookmates");
        mailSender.send(email);
    }

    @Override
    public void sendSignUpForEventWaitingListEmail(Event event, User user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("contact.bookmates@gmail.com");
        email.setTo(user.getEmail());
        email.setSubject("Bookmates - Potwierdzenie rejestracji na wydarzenie!");
        email.setText("Drogi użytkowniku, \n\n" +
                "Dziękujemy za zapisanie się na nasze nadchodzące wydarzenie. \n" +
                "W obecnym momencie znajdujesz się na liście rezerwowych. Jeżeli zwolnią się miejsca na to wydarzenie, zostaniesz o tym poinformowany. \n\n" +
                "Informacji dotyczących wydarzenia: \n" +
                "Nazwa wydarzenia: " + event.getTitle() + "\n" +
                "Data wydarzenia: " + event.getEventDate() + "\n" +
                "Opis wydarzenia: " + event.getDescription() + "\n\n" +
                "W przypadku rezygnacji z chęci udziału w wydarzeniu, prosimy o naciśnięcie na poniższy link: \n\n" +
                "http://localhost:3000/events/" + event.getId() + "/resign \n\n" +
                "Dziękujemy, że jesteś częścią społeczności miłośników książek. \n\n" +
                "Pozdrawiamy,\n " +
                "Zespół Bookmates");
        mailSender.send(email);
    }

    @Override
    public void sendEventResignationEmail(Event event, User user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("contact.bookmates@gmail.com");
        email.setTo(user.getEmail());
        email.setSubject("Bookmates - Potwierdzenie rezygnacji z wydarzenia!");
        email.setText("Drogi użytkowniku, \n\n" +
                "Potwierdzamy Twoją rezygnację z udziału w wybranym wydarzeniu. \n\n" +
                "Informacji dotyczących wydarzenia: \n" +
                "Nazwa wydarzenia: " + event.getTitle() + "\n" +
                "Data wydarzenia: " + event.getEventDate() + "\n" +
                "Opis wydarzenia: " + event.getDescription() + "\n\n" +
                "Zapraszamy do przeglądania innych wydarzeń. \n\n" +
                "Pozdrawiamy,\n " +
                "Zespół Bookmates");
        mailSender.send(email);
    }

    @Override
    public void sendMovingFromWaitingListToParticipantEmail(Event event, User user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("contact.bookmates@gmail.com");
        email.setTo(user.getEmail());
        email.setSubject("Bookmates - Potwierdzenie udziału w wydarzeniu! ");
        email.setText("Drogi użytkowniku, \n\n" +
                "Z radością informujemy Cię, że masz teraz możliwość uczestniczenia w nadchodzącym wydarzeniu, na które wcześniej byłeś zapisany(a) na liście rezerwowych.\n\n" +
                "Informacji dotyczących wydarzenia: \n" +
                "Nazwa wydarzenia: " + event.getTitle() + "\n" +
                "Data wydarzenia: " + event.getEventDate() + "\n" +
                "Opis wydarzenia: " + event.getDescription() + "\n\n" +
                "W przypadku rezygnacji z udziału w wydarzeniu, prosimy o naciśnięcie na poniższy link: \n\n" +
                "http://localhost:3000/events/" + event.getId() + "/resign \n\n" +
                "Dziękujemy, że jesteś częścią społeczności miłośników książek. \n\n" +
                "Pozdrawiamy,\n " +
                "Zespół Bookmates");
        mailSender.send(email);
    }
}
