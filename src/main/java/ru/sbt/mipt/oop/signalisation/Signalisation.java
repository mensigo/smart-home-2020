package ru.sbt.mipt.oop.signalisation;

public interface Signalisation {
    void activate(String enteredCode);

    void deactivate(String enteredCode);

    void alarm();
}
