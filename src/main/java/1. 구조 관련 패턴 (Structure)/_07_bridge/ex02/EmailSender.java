package org._07_bridge.ex02;

class EmailSender implements MessageSender {
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending email with message: " + message);
    }
}