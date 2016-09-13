package godziszewski.patryk.ElectronicsStore.service;

import godziszewski.patryk.ElectronicsStore.model.Order;

public interface MailService {
    void sendEmail(Order order);
}
