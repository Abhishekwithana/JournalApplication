package net.engineeringabhishek.journalApp.Service;

import net.engineeringabhishek.journalApp.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    EmailService emailService;

    @Test
    public void sendEmailTest() {
        emailService.sendEmail("labeyo7281@bankrau.com",
                "Congratulations!!",
                "How yoouuu doiiing?");
    }
}
