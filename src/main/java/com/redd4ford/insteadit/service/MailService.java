package com.redd4ford.insteadit.service;

import com.redd4ford.insteadit.exception.InsteaditException;
import com.redd4ford.insteadit.model.NotificationEmail;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {

  private final JavaMailSender mailSender;
  private final MailContentBuilder mailContentBuilder;

  private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AuthService.class);

  public MailService(JavaMailSender mailSender, MailContentBuilder mailContentBuilder) {
    this.mailSender = mailSender;
    this.mailContentBuilder = mailContentBuilder;
  }

  @Async
  void sendMail(NotificationEmail notificationEmail) {
    MimeMessagePreparator messagePreparator = mimeMessage -> {
      MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
      messageHelper.setFrom("insteadit@email.com");
      messageHelper.setTo(notificationEmail.getRecipient());
      messageHelper.setSubject(notificationEmail.getSubject());
      messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
    };
    try {
      mailSender.send(messagePreparator);
      log.info("Activation email sent!!");
    } catch (MailException e) {
      throw new InsteaditException("Exception occurred when sending mail to " + notificationEmail.getRecipient());
    }
  }

}
