package com.tcc.api.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerErrorException;

@Component
public class SendMail{

    @Autowired
    protected JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            String htmlText = GenerateHTMLMessage.signup(text);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlText, true);
            emailSender.send(message);
        } catch (MessagingException e){
            throw new ServerErrorException(e.getMessage(), null);
        }
    }
}
