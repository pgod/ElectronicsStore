package godziszewski.patryk.ElectronicsStore.service;

import godziszewski.patryk.ElectronicsStore.domain.Order;

public interface MailService {
    void sendEmail(Order order);
}
