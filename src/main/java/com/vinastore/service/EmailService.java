package com.vinastore.service;

import jakarta.mail.MessagingException;
import org.springframework.mail.MailException;

import java.util.concurrent.CompletableFuture;

public interface EmailService {
    public CompletableFuture<Void>sendEmail(String subject, String recipient, String content) throws MessagingException;
}
