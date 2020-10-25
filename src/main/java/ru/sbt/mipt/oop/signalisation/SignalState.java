package ru.sbt.mipt.oop.signalisation;

public interface SignalState {
    String getName();

    void activate(String enteredCode);

    void deactivate(String enteredCode);

    void alarm();
}
