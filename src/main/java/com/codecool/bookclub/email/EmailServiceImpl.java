package com.codecool.bookclub.email;

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
    public void sendRegistrationEmail() {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("contact.bookmates@gmail.com");
        email.setTo("contact.bookmates@gmail.com");
        email.setSubject("Bookmates - Pomyślna rejestracja!");
        email.setText("Drogi użytkowniku, \n\n" +
                "Twoja rejestracja zakończyła się sukcesem. \n" +
                "Jesteśmy podekscytowani, że dołączyłeś do naszej dynamicznej społeczności miłośników książek. \n\n " +
                "Pozdrawiamy, \n " +
                "Zespół Bookmates");
        mailSender.send(email);
    }

    @Override
    public void sendNewEventCreatedEmail() {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("contact.bookmates@gmail.com");
        email.setTo("contact.bookmates@gmail.com");
        email.setSubject("Bookmates - Potwierdzenie utworzenia wydarzenia!");
        email.setText("Drogi użytkowniku, \n\n" +
                "Twoje wydarzenie zostało pomyślnie utworzone i jest teraz widoczne dla innych użytkowników. \n\n" +
                /*TODO dodać dane wydarzenia  */
                "Jeśli masz jakiekolwiek pytania, " +
                "potrzebujesz wprowadzić zmiany lub skasować wydarzenie, prosimy o kontakt. " +
                "Nasz zespół jest gotowy do udzielenia Ci wsparcia i odpowiedzi na wszystkie pytania. \n" +
                "Aby skontaktować się z nami, możesz odpowiedzieć na tę wiadomość email lub " +
                "skorzystać z formularza kontaktowego dostępnego na naszej stronie internetowej." +
                "Dziękujemy, że jesteś częścią społeczności miłośników książek. \n\n" +
                "Pozdrawiamy, \n " +
                "Zespół Bookmates");
        mailSender.send(email);
    }

    @Override
    public void sendJoinEventEmail() {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("contact.bookmates@gmail.com");
        email.setTo("contact.bookmates@gmail.com");
        email.setSubject("Bookmates - Potwierdzenie rejestracji na wydarzenie!");
        email.setText("Drogi użytkowniku, \n\n" +
                "Dziękujemy za zapisanie się na nasze nadchodzące wydarzenie. \n\n" +
                "Informacji dotyczących wydarzenia: \n" +
                /*TODO dodać dane wydarzenia  */
                "W przypadku rezygnacji z udziału w wydarzeniu, prosimy o naciśnięcie na poniższy link: \n\n" +
                /*TODO dodać link  */
                "Dziękujemy, że jesteś częścią społeczności miłośników książek. \n\n" +
                "Pozdrawiamy, \n " +
                "Zespół Bookmates");
        mailSender.send(email);
    }
}
