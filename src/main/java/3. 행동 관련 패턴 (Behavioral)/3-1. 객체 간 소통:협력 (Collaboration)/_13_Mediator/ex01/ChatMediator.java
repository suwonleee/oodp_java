package org._13_Mediator.ex01;

// Mediator interface
public interface ChatMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}