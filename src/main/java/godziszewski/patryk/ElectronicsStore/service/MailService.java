package godziszewski.patryk.ElectronicsStore.service;

import org.springframework.mail.javamail.MimeMessagePreparator;

import godziszewski.patryk.ElectronicsStore.domain.Order;

public interface MailService {
    void sendEmail(Order order);
    MimeMessagePreparator getMessagePreparator(Order order) ;
}
