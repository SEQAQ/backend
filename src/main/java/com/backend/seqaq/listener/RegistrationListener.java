package com.backend.seqaq.listener;

import com.backend.seqaq.entity.Users;
import com.backend.seqaq.event.OnRegistrationCompletedEvent;
import com.backend.seqaq.service.ConfirmationTokenService;
import com.backend.seqaq.service.UsersService;
import com.backend.seqaq.util.exception.RegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompletedEvent> {

  @Autowired private UsersService service;
  @Autowired private ConfirmationTokenService tokenService;
  @Autowired private JavaMailSender mailSender;
  @Autowired private Environment env;

  @Override
  public void onApplicationEvent(OnRegistrationCompletedEvent event) {
    try {
      this.confirmRegistration(event);
    } catch (RegistrationException e) {
      e.printStackTrace();
    }
  }

  private void confirmRegistration(OnRegistrationCompletedEvent event)
      throws RegistrationException {
    String senderAddr = env.getProperty("spring.mail.username");
    if (senderAddr == null) throw new RegistrationException("Email service failure");

    Users user = event.getUser();
    String tokenString = UUID.randomUUID().toString();

    String recipientAddress = user.getEmail();
    String subject = "开启 QAQ 世界的大门";
    String text =
        "Hi, "
            + (user.getRname() == null ? "" : user.getRname())
            + "!\r\n\r\n 🔗 http://api.uniqaq.tk/users/activate?token="
            + tokenString;
    SimpleMailMessage email = new SimpleMailMessage();
    email.setFrom(senderAddr);
    email.setTo(recipientAddress);
    email.setSubject(subject);
    email.setText(text);
    mailSender.send(email);
    System.out.println("Successfully sent email to " + email);
    tokenService.saveNewToken(user, tokenString);
  }
}
