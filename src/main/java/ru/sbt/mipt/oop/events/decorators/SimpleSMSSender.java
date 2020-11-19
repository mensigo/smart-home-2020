package ru.sbt.mipt.oop.events.decorators;

public class SimpleSMSSender implements SMSSender {
    @Override
    public void sendSMS(String message) {
        System.out.println("Sending sms.. // " + message);
    }
}
