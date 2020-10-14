package ru.sbt.mipt.oop.signalisation;

public interface Signalisation {

    String getState();

    void setState(String stateName);

    void setState(String stateName, String enteredCode);

    void activate(String enteredCode);

    void deactivate(String enteredCode);

    void alarm();
}
